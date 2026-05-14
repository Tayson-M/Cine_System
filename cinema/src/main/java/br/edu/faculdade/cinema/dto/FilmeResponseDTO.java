package br.edu.faculdade.cinema.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FilmeResponseDTO {
    private Long id;
    private String titulo;
    private String genero;
    private Integer duracao;
    private String classificacao;
    private String sinopse;
    private boolean emCartaz;
}
