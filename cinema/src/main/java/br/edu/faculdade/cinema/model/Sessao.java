package br.edu.faculdade.cinema.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessoes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "filme_id")
    private Filme filme;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Double preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSessao status;

    public boolean isDisponivel() {
        return status == StatusSessao.ABERTA && dataHora.isAfter(LocalDateTime.now());
    }
}
