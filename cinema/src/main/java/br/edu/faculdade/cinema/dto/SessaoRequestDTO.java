package br.edu.faculdade.cinema.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SessaoRequestDTO {

    @NotNull(message = "Filme é obrigatório")
    private Long filmeId;

    @NotNull(message = "Sala é obrigatória")
    private Long salaId;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private Double preco;
}
