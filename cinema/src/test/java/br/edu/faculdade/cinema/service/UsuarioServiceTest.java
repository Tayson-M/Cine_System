package br.edu.faculdade.cinema.service;

import br.edu.faculdade.cinema.dto.UsuarioRequestDTO;
import br.edu.faculdade.cinema.dto.UsuarioResponseDTO;
import br.edu.faculdade.cinema.exception.RegraDeNegocioException;
import br.edu.faculdade.cinema.model.Cargo;
import br.edu.faculdade.cinema.model.Usuario;
import br.edu.faculdade.cinema.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários — UsuarioService")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioAdmin;
    private UsuarioRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        usuarioAdmin = Usuario.builder()
                .id(1L).login("admin").senha("$2a$10$hash").cargo(Cargo.ADMIN).build();

        requestDTO = UsuarioRequestDTO.builder()
                .login("novouser").senha("senha123").cargo(Cargo.USER).build();
    }

    @Test
    @DisplayName("loadUserByUsername — deve retornar UserDetails quando login existe")
    void loadUserByUsername_deveRetornarUserDetails_quandoLoginExiste() {
        when(usuarioRepository.findByLogin("admin")).thenReturn(Optional.of(usuarioAdmin));

        var userDetails = usuarioService.loadUserByUsername("admin");

        assertThat(userDetails.getUsername()).isEqualTo("admin");
        assertThat(userDetails.getPassword()).isEqualTo("$2a$10$hash");
        assertThat(userDetails.getAuthorities())
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    @Test
    @DisplayName("loadUserByUsername — deve lançar UsernameNotFoundException quando login não existe")
    void loadUserByUsername_deveLancarExcecao_quandoLoginNaoExiste() {
        when(usuarioRepository.findByLogin("inexistente")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usuarioService.loadUserByUsername("inexistente"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("inexistente");
    }

    @Test
    @DisplayName("cadastrar — deve salvar e retornar DTO quando login disponível")
    void cadastrar_deveSalvarUsuario_quandoLoginDisponivel() {
        when(usuarioRepository.existsByLogin("novouser")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> {
            Usuario u = inv.getArgument(0);
            return Usuario.builder().id(2L).login(u.getLogin()).senha(u.getSenha()).cargo(u.getCargo()).build();
        });

        UsuarioResponseDTO response = usuarioService.cadastrar(requestDTO, "$2a$10$encodedHash");

        assertThat(response.getId()).isEqualTo(2L);
        assertThat(response.getLogin()).isEqualTo("novouser");
        assertThat(response.getCargo()).isEqualTo(Cargo.USER);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    @DisplayName("cadastrar — deve lançar RegraDeNegocioException quando login já existe")
    void cadastrar_deveLancarExcecao_quandoLoginJaExiste() {
        when(usuarioRepository.existsByLogin("novouser")).thenReturn(true);

        assertThatThrownBy(() -> usuarioService.cadastrar(requestDTO, "qualquerHash"))
                .isInstanceOf(RegraDeNegocioException.class)
                .hasMessageContaining("novouser");

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("listarTodos — deve retornar lista com todos os usuários")
    void listarTodos_deveRetornarListaDeUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuarioAdmin));

        assertThat(usuarioService.listarTodos()).hasSize(1);
    }

    @Test
    @DisplayName("listarTodos — deve retornar lista vazia quando não há usuários")
    void listarTodos_deveRetornarListaVazia_quandoNaoHaUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of());

        assertThat(usuarioService.listarTodos()).isEmpty();
    }

    @Test
    @DisplayName("deletar — deve remover usuário quando id existe")
    void deletar_deveRemoverUsuario_quandoIdExiste() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        usuarioService.deletar(1L);

        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    @DisplayName("deletar — deve lançar UsuarioNotFoundException quando id não existe")
    void deletar_deveLancarExcecao_quandoIdNaoExiste() {
        when(usuarioRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> usuarioService.deletar(99L))
                .isInstanceOf(br.edu.faculdade.cinema.exception.UsuarioNotFoundException.class)
                .hasMessageContaining("99");

        verify(usuarioRepository, never()).deleteById(any());
    }
}