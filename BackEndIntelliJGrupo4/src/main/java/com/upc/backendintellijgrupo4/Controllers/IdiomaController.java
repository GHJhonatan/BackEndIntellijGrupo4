package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.IdiomaDTO;
import com.upc.backendintellijgrupo4.Entities.Idioma;
import com.upc.backendintellijgrupo4.Services.IdiomaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/idiomas")
public class IdiomaController {

    private final IdiomaService idiomaService;

    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    @GetMapping
    public List<Idioma> listar() {
        return idiomaService.listar();
    }

    @PostMapping
    public Idioma guardar(@RequestBody IdiomaDTO idiomaDTO) {
        return idiomaService.guardar(idiomaDTO);
    }
}