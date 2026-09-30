package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.ProgresoLeccionDTO;
import com.upc.backendintellijgrupo4.Entities.ProgresoLeccion;
import com.upc.backendintellijgrupo4.Repositories.ProgresoLeccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgresoLeccionService {

    private final ProgresoLeccionRepository repository;

    public ProgresoLeccionService(ProgresoLeccionRepository repository) {
        this.repository = repository;
    }

    public List<ProgresoLeccionDTO> listar() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public ProgresoLeccionDTO obtener(Long id) {
        return toDTO(buscar(id));
    }

    public List<ProgresoLeccionDTO> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream().map(this::toDTO).toList();
    }

    public ProgresoLeccionDTO crear(ProgresoLeccionDTO dto) {
        ProgresoLeccion entidad = new ProgresoLeccion();
        entidad.setId(null);
        copiar(dto, entidad);
        return toDTO(repository.save(entidad));
    }

    public ProgresoLeccionDTO actualizar(Long id, ProgresoLeccionDTO dto) {
        ProgresoLeccion entidad = buscar(id);
        copiar(dto, entidad);
        return toDTO(repository.save(entidad));
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private ProgresoLeccion buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgresoLeccion no encontrado con id " + id));
    }

    private void copiar(ProgresoLeccionDTO dto, ProgresoLeccion entidad) {
        entidad.setUsuarioId(dto.getUsuarioId());
        entidad.setLeccionId(dto.getLeccionId());
        entidad.setEstado(dto.getEstado() != null ? dto.getEstado() : ProgresoLeccion.Estado.BLOQUEADA);
        entidad.setPorcentajeAvance(dto.getPorcentajeAvance());
        entidad.setTiempoEstudioMin(dto.getTiempoEstudioMin());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaCompletado(dto.getFechaCompletado());
    }

    private ProgresoLeccionDTO toDTO(ProgresoLeccion e) {
        return new ProgresoLeccionDTO(e.getId(), e.getUsuarioId(), e.getLeccionId(), e.getEstado(),
                e.getPorcentajeAvance(), e.getTiempoEstudioMin(), e.getFechaInicio(), e.getFechaCompletado());
    }
}
