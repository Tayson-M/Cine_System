package br.edu.faculdade.cinema.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private Integer capacidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSala tipo;
}
