package br.edu.faculdade.cinema.dto;

import br.edu.faculdade.cinema.model.TipoCliente;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private TipoCliente tipoCliente;
    private boolean temMeiaEntrada;
}
