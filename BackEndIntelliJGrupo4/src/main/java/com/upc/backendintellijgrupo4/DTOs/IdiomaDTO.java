package com.upc.backendintellijgrupo4.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdiomaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String iconoUrl;
}