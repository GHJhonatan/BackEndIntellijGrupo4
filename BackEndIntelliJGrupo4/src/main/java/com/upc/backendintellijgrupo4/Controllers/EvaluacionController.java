package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.EvaluacionDTO;
import com.upc.backendintellijgrupo4.Entities.Evaluacion;
import com.upc.backendintellijgrupo4.Services.EvaluacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    @PostMapping
    public Evaluacion guardar(@RequestBody EvaluacionDTO evaluacionDTO) {
        return evaluacionService.guardar(evaluacionDTO);
    }
}