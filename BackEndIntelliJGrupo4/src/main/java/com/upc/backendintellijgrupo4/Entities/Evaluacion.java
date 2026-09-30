package com.upc.backendintellijgrupo4.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "leccion_id")
    private Leccion leccion;

    private String titulo;

    @Column(name = "cantidad_preguntas")
    private Integer cantidadPreguntas;

    @Column(name = "duracion_min")
    private Integer duracionMin;
}