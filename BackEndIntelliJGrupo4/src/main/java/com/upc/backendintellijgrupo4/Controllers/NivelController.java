package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.NivelDTO;
import com.upc.backendintellijgrupo4.Entities.Nivel;
import com.upc.backendintellijgrupo4.Services.NivelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/niveles")

public class NivelController {

    private final NivelService nivelService;

    public NivelController(NivelService nivelService) {
        this.nivelService = nivelService;
    }

    @GetMapping
    public List<Nivel> listar() {
        return nivelService.listar();
    }

    @PostMapping
    public Nivel guardar(@RequestBody NivelDTO nivelDTO) {
        return nivelService.guardar(nivelDTO);
    }
}
