package br.edu.faculdade.cinema.dto;

import br.edu.faculdade.cinema.model.StatusSessao;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SessaoResponseDTO {
    private Long id;
    private String tituloFilme;
    private Integer numeroSala;
    private LocalDateTime dataHora;
    private Double preco;
    private StatusSessao status;
    private Integer capacidadeSala;
    private Long reservasAtivas;
    private Integer assentosDisponiveis;
}
