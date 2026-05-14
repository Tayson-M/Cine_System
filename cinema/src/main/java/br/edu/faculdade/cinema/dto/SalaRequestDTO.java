package br.edu.faculdade.cinema.dto;

import br.edu.faculdade.cinema.model.TipoSala;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SalaRequestDTO {

    @NotNull(message = "Número é obrigatório")
    @Min(value = 1)
    private Integer numero;

    @NotNull(message = "Capacidade é obrigatória")
    @Min(value = 1, message = "Capacidade deve ser maior que 0")
    @Max(value = 500)
    private Integer capacidade;

    @NotNull(message = "Tipo é obrigatório")
    private TipoSala tipo;
}
