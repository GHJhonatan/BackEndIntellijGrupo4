package com.upc.backendintellijgrupo4.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    private String nombres;

    private String apellidos;

    private String correo;

    private String passwordHash;

    private Long rolId;

    private String estado;
}