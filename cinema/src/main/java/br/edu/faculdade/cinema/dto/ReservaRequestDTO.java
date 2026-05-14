package br.edu.faculdade.cinema.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservaRequestDTO {

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "Sessão é obrigatória")
    private Long sessaoId;
}
