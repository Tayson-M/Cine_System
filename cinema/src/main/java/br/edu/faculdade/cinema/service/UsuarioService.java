package br.edu.faculdade.cinema.service;

import br.edu.faculdade.cinema.dto.UsuarioRequestDTO;
import br.edu.faculdade.cinema.dto.UsuarioResponseDTO;
import br.edu.faculdade.cinema.exception.RegraDeNegocioException;
import br.edu.faculdade.cinema.exception.UsuarioNotFoundException;
import br.edu.faculdade.cinema.model.Usuario;
import br.edu.faculdade.cinema.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getCargo().name())
                .build();
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto, String senhaCodificada) {
        if (usuarioRepository.existsByLogin(dto.getLogin())) {
            throw new RegraDeNegocioException("Login '" + dto.getLogin() + "' já está em uso.");
        }
        Usuario usuario = Usuario.builder()
                .login(dto.getLogin())
                .senha(senhaCodificada)
                .cargo(dto.getCargo())
                .build();
        Usuario salvo = usuarioRepository.save(usuario);
        log.info("Usuário cadastrado: {}", salvo.getLogin());
        return toResponseDTO(salvo);
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException(id);
        }
        usuarioRepository.deleteById(id);
        log.info("Usuário {} removido.", id);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario u) {
        return UsuarioResponseDTO.builder()
                .id(u.getId())
                .login(u.getLogin())
                .cargo(u.getCargo())
                .build();
    }
}