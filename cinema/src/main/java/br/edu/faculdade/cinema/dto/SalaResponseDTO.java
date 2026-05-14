package br.edu.faculdade.cinema.dto;

import br.edu.faculdade.cinema.model.TipoSala;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SalaResponseDTO {
    private Long id;
    private Integer numero;
    private Integer capacidade;
    private TipoSala tipo;
}
