package br.edu.faculdade.cinema.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingressos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @Column(nullable = false)
    private Integer numeroAssento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoIngresso tipo;

    @Column(nullable = false)
    private Double valorPago;
}
