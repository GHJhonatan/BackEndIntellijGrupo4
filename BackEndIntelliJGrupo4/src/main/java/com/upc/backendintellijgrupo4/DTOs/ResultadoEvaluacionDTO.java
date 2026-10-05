package com.upc.backendintellijgrupo4.DTOs;

import com.upc.backendintellijgrupo4.Entities.ResultadoEvaluacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoEvaluacionDTO {
    private Long id;
    private Long usuarioId;
    private Long evaluacionId;
    private Integer respuestasCorrectas;
    private Integer totalPreguntas;
    private BigDecimal puntajePorcentaje;
    private Boolean aprobada;
    private ResultadoEvaluacion.Estado estado;
    private LocalDateTime fecha;
}
