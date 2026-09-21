package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.UsuarioDTO;
import com.upc.backendintellijgrupo4.Entities.Usuario;
import com.upc.backendintellijgrupo4.Services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @PostMapping
    public Usuario guardar(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.guardar(usuarioDTO);
    }
}