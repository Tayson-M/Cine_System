package br.edu.faculdade.cinema.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FilmeRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    private String titulo;

    @NotBlank(message = "Gênero é obrigatório")
    private String genero;

    @NotNull(message = "Duração é obrigatória")
    @Min(value = 1, message = "Duração deve ser maior que 0")
    private Integer duracao;

    @NotBlank(message = "Classificação é obrigatória")
    private String classificacao;

    private String sinopse;
    private boolean emCartaz;
}
