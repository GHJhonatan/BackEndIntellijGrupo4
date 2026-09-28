package com.upc.backendintellijgrupo4.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LeccionDTO {

    private Long id;
    private Long idiomaId;
    private Long nivelId;
    private String titulo;
    private String descripcion;
    private String tipoContenido;
    private String urlContenido;
    private Integer duracionMin;
    private Integer orden;
}
