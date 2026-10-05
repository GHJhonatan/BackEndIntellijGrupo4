package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.ProgresoLeccionDTO;
import com.upc.backendintellijgrupo4.Services.ProgresoLeccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progreso-lecciones")
public class ProgresoLeccionController {

    private final ProgresoLeccionService service;

    public ProgresoLeccionController(ProgresoLeccionService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProgresoLeccionDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProgresoLeccionDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ProgresoLeccionDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<ProgresoLeccionDTO> crear(@RequestBody ProgresoLeccionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ProgresoLeccionDTO actualizar(@PathVariable Long id, @RequestBody ProgresoLeccionDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
