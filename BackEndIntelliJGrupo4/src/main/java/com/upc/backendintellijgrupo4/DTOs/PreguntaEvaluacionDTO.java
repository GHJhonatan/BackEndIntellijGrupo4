package com.upc.backendintellijgrupo4.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaEvaluacionDTO {

    private Long id;
    private Long evaluacionId;
    private String enunciado;
    private String tipo;
    private String respuestaCorrecta;
}