package br.edu.faculdade.cinema.config;

import br.edu.faculdade.cinema.model.Cargo;
import br.edu.faculdade.cinema.model.Usuario;
import br.edu.faculdade.cinema.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (usuarioRepository.findByLogin("admin").isEmpty()) {
                usuarioRepository.save(Usuario.builder()
                    .login("admin")
                    .senha(passwordEncoder.encode("admin123"))
                    .cargo(Cargo.ADMIN)
                    .build());
                log.info("==> Usuário padrão criado: login=admin | senha=admin123");
            }
        };
    }
}
