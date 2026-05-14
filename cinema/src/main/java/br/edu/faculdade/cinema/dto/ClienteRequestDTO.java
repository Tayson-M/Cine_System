package br.edu.faculdade.cinema.dto;

import br.edu.faculdade.cinema.model.TipoCliente;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150)
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotNull(message = "Tipo de cliente é obrigatório")
    private TipoCliente tipoCliente;
}
