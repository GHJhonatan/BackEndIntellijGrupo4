package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.OpcionPreguntaDTO;
import com.upc.backendintellijgrupo4.Entities.OpcionPregunta;
import com.upc.backendintellijgrupo4.Entities.PreguntaEvaluacion;
import com.upc.backendintellijgrupo4.Repositories.OpcionPreguntaRepository;
import com.upc.backendintellijgrupo4.Repositories.PreguntaEvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpcionPreguntaService {

    @Autowired
    private OpcionPreguntaRepository opcionPreguntaRepository;

    @Autowired
    private PreguntaEvaluacionRepository preguntaEvaluacionRepository;

    public List<OpcionPregunta> listar() {
        return opcionPreguntaRepository.findAll();
    }

    public OpcionPregunta guardar(OpcionPreguntaDTO dto) {
        PreguntaEvaluacion pregunta = preguntaEvaluacionRepository.findById(dto.getPreguntaId())
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));

        OpcionPregunta opcion = new OpcionPregunta();
        opcion.setPregunta(pregunta);
        opcion.setTextoOpcion(dto.getTextoOpcion());
        opcion.setEsCorrecta(dto.getEsCorrecta());

        return opcionPreguntaRepository.save(opcion);
    }
}