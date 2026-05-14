package br.edu.faculdade.cinema.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filmes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(nullable = false)
    private Integer duracao;

    @Column(nullable = false, length = 10)
    private String classificacao;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    @Column(nullable = false)
    private boolean emCartaz;
}
