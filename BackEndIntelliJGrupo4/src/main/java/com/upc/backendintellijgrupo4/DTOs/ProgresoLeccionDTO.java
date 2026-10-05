package com.upc.backendintellijgrupo4.DTOs;

import com.upc.backendintellijgrupo4.Entities.ProgresoLeccion;
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
public class ProgresoLeccionDTO {
    private Long id;
    private Long usuarioId;
    private Long leccionId;
    private ProgresoLeccion.Estado estado;
    private BigDecimal porcentajeAvance;
    private Integer tiempoEstudioMin;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaCompletado;
}
