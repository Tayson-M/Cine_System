package br.edu.faculdade.cinema.dto;

import br.edu.faculdade.cinema.model.Cargo;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UsuarioResponseDTO {
    private Long id;
    private String login;
    private Cargo cargo;
}
