package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.PreguntaEvaluacionDTO;
import com.upc.backendintellijgrupo4.Entities.PreguntaEvaluacion;
import com.upc.backendintellijgrupo4.Repositories.PreguntaEvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaEvaluacionService {

    @Autowired
    private PreguntaEvaluacionRepository preguntaEvaluacionRepository;

    public List<PreguntaEvaluacion> listar() {
        return preguntaEvaluacionRepository.findAll();
    }

    public PreguntaEvaluacion guardar(PreguntaEvaluacionDTO dto) {
        PreguntaEvaluacion pregunta = new PreguntaEvaluacion();
        pregunta.setEnunciado(dto.getEnunciado());
        pregunta.setTipo(dto.getTipo());
        pregunta.setRespuestaCorrecta(dto.getRespuestaCorrecta());

        return preguntaEvaluacionRepository.save(pregunta);
    }
}