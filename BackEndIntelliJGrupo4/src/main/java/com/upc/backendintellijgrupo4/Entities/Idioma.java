package com.upc.backendintellijgrupo4.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "idiomas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @Column(name = "icono_url")
    private String iconoUrl;
}