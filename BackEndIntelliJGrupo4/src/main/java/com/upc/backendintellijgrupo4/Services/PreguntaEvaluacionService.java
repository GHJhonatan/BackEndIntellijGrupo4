package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.PreguntaEvaluacionDTO;
import com.upc.backendintellijgrupo4.Entities.Evaluacion;
import com.upc.backendintellijgrupo4.Entities.PreguntaEvaluacion;
import com.upc.backendintellijgrupo4.Repositories.EvaluacionRepository;
import com.upc.backendintellijgrupo4.Repositories.PreguntaEvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaEvaluacionService {

    private final PreguntaEvaluacionRepository preguntaEvaluacionRepository;
    private final EvaluacionRepository evaluacionRepository;

    public PreguntaEvaluacionService(
            PreguntaEvaluacionRepository preguntaEvaluacionRepository,
            EvaluacionRepository evaluacionRepository) {

        this.preguntaEvaluacionRepository = preguntaEvaluacionRepository;
        this.evaluacionRepository = evaluacionRepository;
    }

    public List<PreguntaEvaluacion> listar() {
        return preguntaEvaluacionRepository.findAll();
    }

    public PreguntaEvaluacion guardar(PreguntaEvaluacionDTO dto) {

        PreguntaEvaluacion pregunta = new PreguntaEvaluacion();

        Evaluacion evaluacion = evaluacionRepository.findById(dto.getEvaluacionId())
                .orElseThrow(() -> new RuntimeException("Evaluacion no encontrada"));

        pregunta.setEvaluacion(evaluacion);
        pregunta.setEnunciado(dto.getEnunciado());
        pregunta.setTipo(dto.getTipo());
        pregunta.setRespuestaCorrecta(dto.getRespuestaCorrecta());

        return preguntaEvaluacionRepository.save(pregunta);
    }
}