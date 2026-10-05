package com.upc.backendintellijgrupo4.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionDTO {

    private Long id;

    private Long leccionId;

    private String titulo;

    private Integer cantidadPreguntas;

    private Integer duracionMin;
}