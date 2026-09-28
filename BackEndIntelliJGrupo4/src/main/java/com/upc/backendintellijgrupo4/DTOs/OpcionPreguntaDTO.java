package com.upc.backendintellijgrupo4.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpcionPreguntaDTO {

    private Long id;
    private Long preguntaId;
    private String textoOpcion;
    private Boolean esCorrecta;
}