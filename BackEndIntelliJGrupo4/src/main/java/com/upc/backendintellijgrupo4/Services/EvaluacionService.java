package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.EvaluacionDTO;
import com.upc.backendintellijgrupo4.Entities.Evaluacion;
import com.upc.backendintellijgrupo4.Entities.Leccion;
import com.upc.backendintellijgrupo4.Repositories.EvaluacionRepository;
import com.upc.backendintellijgrupo4.Repositories.LeccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final LeccionRepository leccionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository,
                             LeccionRepository leccionRepository) {
        this.evaluacionRepository = evaluacionRepository;
        this.leccionRepository = leccionRepository;
    }

    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion guardar(EvaluacionDTO evaluacionDTO) {

        Evaluacion evaluacion = new Evaluacion();

        Leccion leccion = leccionRepository.findById(evaluacionDTO.getLeccionId())
                .orElseThrow(() -> new RuntimeException("Leccion no encontrada"));

        evaluacion.setLeccion(leccion);
        evaluacion.setTitulo(evaluacionDTO.getTitulo());
        evaluacion.setCantidadPreguntas(evaluacionDTO.getCantidadPreguntas());
        evaluacion.setDuracionMin(evaluacionDTO.getDuracionMin());

        return evaluacionRepository.save(evaluacion);
    }
}