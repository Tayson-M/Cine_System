package br.edu.faculdade.cinema.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataReserva;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    @Column(nullable = false)
    private Double valorTotal;

    @PrePersist
    protected void onCreate() {
        this.dataReserva = LocalDateTime.now();
    }
}
