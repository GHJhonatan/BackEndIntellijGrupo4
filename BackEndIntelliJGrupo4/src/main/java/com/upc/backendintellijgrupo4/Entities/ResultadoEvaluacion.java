package com.upc.backendintellijgrupo4.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "resultados_evaluacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoEvaluacion {

    public enum Estado {
        PENDIENTE, EN_PROGRESO, COMPLETADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "evaluacion_id", nullable = false)
    private Long evaluacionId;

    @Column(name = "respuestas_correctas")
    private Integer respuestasCorrectas;

    @Column(name = "total_preguntas")
    private Integer totalPreguntas;

    @Column(name = "puntaje_porcentaje", precision = 5, scale = 2)
    private BigDecimal puntajePorcentaje;

    @Column(name = "aprobada")
    private Boolean aprobada;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}
