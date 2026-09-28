package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.LeccionDTO;
import com.upc.backendintellijgrupo4.Entities.Leccion;
import com.upc.backendintellijgrupo4.Services.LeccionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecciones")

public class LeccionController {

    private final LeccionService leccionService;

    public LeccionController(LeccionService leccionService) {
        this.leccionService = leccionService;
    }

    @GetMapping
    public List<Leccion> listar() {
        return leccionService.listar();
    }

    @PostMapping
    public Leccion guardar(@RequestBody LeccionDTO leccionDTO) {
        return leccionService.guardar(leccionDTO);
    }

}
