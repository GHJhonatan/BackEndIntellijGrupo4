package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.ResultadoEvaluacionDTO;
import com.upc.backendintellijgrupo4.Services.ResultadoEvaluacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultados-evaluacion")
public class ResultadoEvaluacionController {

    private final ResultadoEvaluacionService service;

    public ResultadoEvaluacionController(ResultadoEvaluacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResultadoEvaluacionDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResultadoEvaluacionDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ResultadoEvaluacionDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @GetMapping("/evaluacion/{evaluacionId}")
    public List<ResultadoEvaluacionDTO> listarPorEvaluacion(@PathVariable Long evaluacionId) {
        return service.listarPorEvaluacion(evaluacionId);
    }

    @PostMapping
    public ResponseEntity<ResultadoEvaluacionDTO> crear(@RequestBody ResultadoEvaluacionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResultadoEvaluacionDTO actualizar(@PathVariable Long id, @RequestBody ResultadoEvaluacionDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
