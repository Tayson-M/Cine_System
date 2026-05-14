package br.edu.faculdade.cinema.dto;

import br.edu.faculdade.cinema.model.Cargo;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UsuarioRequestDTO {

    @NotBlank(message = "Login é obrigatório")
    @Size(min = 3, max = 50, message = "Login deve ter entre 3 e 50 caracteres")
    private String login;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull(message = "Cargo é obrigatório")
    private Cargo cargo;
}
