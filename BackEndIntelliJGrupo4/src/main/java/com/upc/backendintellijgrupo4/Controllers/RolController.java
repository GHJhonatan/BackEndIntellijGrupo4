package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.RolDTO;
import com.upc.backendintellijgrupo4.Entities.Rol;
import com.upc.backendintellijgrupo4.Services.RolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<Rol> listar() {
        return rolService.listar();
    }

    @PostMapping
    public Rol guardar(@RequestBody RolDTO rolDTO) {
        return rolService.guardar(rolDTO);
    }
}