package com.upc.backendintellijgrupo4.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "preguntas_evaluacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id")
    private Evaluacion evaluacion;

    private String enunciado;

    private String tipo;

    @Column(name = "respuesta_correcta")
    private String respuestaCorrecta;
}