package com.upc.backendintellijgrupo4.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Leccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idioma_id")
    private Idioma idioma;

    @ManyToOne
    @JoinColumn(name = "nivel_id")
    private Nivel nivel;

    private String titulo;

    private String descripcion;

    @Column(name = "tipo_contenido")
    private String tipoContenido;

    @Column(name = "url_contenido")
    private String urlContenido;

    @Column(name = "duracion_min")
    private Integer duracionMin;

    private Integer orden;
}
