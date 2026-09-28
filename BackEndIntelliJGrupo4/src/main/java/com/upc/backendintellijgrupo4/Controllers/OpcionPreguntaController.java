package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.OpcionPreguntaDTO;
import com.upc.backendintellijgrupo4.Entities.OpcionPregunta;
import com.upc.backendintellijgrupo4.Services.OpcionPreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opciones-pregunta")
public class OpcionPreguntaController {

    @Autowired
    private OpcionPreguntaService opcionPreguntaService;

    @GetMapping
    public List<OpcionPregunta> listar() {
        return opcionPreguntaService.listar();
    }

    @PostMapping
    public OpcionPregunta guardar(@RequestBody OpcionPreguntaDTO dto) {
        return opcionPreguntaService.guardar(dto);
    }
}