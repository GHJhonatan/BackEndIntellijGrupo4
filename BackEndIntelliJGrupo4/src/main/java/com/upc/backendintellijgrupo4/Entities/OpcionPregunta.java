package com.upc.backendintellijgrupo4.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "opciones_pregunta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpcionPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private PreguntaEvaluacion pregunta;

    @Column(name = "texto_opcion")
    private String textoOpcion;

    @Column(name = "es_correcta")
    private Boolean esCorrecta;
}