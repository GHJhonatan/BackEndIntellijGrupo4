package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.PreguntaEvaluacionDTO;
import com.upc.backendintellijgrupo4.Entities.PreguntaEvaluacion;
import com.upc.backendintellijgrupo4.Services.PreguntaEvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas-evaluacion")
public class PreguntaEvaluacionController {

    @Autowired
    private PreguntaEvaluacionService preguntaEvaluacionService;

    @GetMapping
    public List<PreguntaEvaluacion> listar() {
        return preguntaEvaluacionService.listar();
    }

    @PostMapping
    public PreguntaEvaluacion guardar(@RequestBody PreguntaEvaluacionDTO dto) {
        return preguntaEvaluacionService.guardar(dto);
    }
}