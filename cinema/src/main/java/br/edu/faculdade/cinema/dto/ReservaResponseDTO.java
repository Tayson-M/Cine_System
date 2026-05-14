package br.edu.faculdade.cinema.dto;

import br.edu.faculdade.cinema.model.StatusReserva;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservaResponseDTO {
    private Long id;
    private String nomeCliente;
    private String tituloFilme;
    private LocalDateTime dataHoraSessao;
    private LocalDateTime dataReserva;
    private StatusReserva status;
    private Double valorTotal;
    private boolean meiaEntrada;
}
