package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.ResultadoEvaluacionDTO;
import com.upc.backendintellijgrupo4.Entities.ResultadoEvaluacion;
import com.upc.backendintellijgrupo4.Repositories.ResultadoEvaluacionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ResultadoEvaluacionService {

    private static final BigDecimal NOTA_MINIMA = new BigDecimal("60");

    private final ResultadoEvaluacionRepository repository;

    public ResultadoEvaluacionService(ResultadoEvaluacionRepository repository) {
        this.repository = repository;
    }

    public List<ResultadoEvaluacionDTO> listar() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public ResultadoEvaluacionDTO obtener(Long id) {
        return toDTO(buscar(id));
    }

    public List<ResultadoEvaluacionDTO> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream().map(this::toDTO).toList();
    }

    public List<ResultadoEvaluacionDTO> listarPorEvaluacion(Long evaluacionId) {
        return repository.findByEvaluacionId(evaluacionId).stream().map(this::toDTO).toList();
    }

    public ResultadoEvaluacionDTO crear(ResultadoEvaluacionDTO dto) {
        ResultadoEvaluacion entidad = new ResultadoEvaluacion();
        copiar(dto, entidad);
        return toDTO(repository.save(entidad));
    }

    public ResultadoEvaluacionDTO actualizar(Long id, ResultadoEvaluacionDTO dto) {
        ResultadoEvaluacion entidad = buscar(id);
        copiar(dto, entidad);
        return toDTO(repository.save(entidad));
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private ResultadoEvaluacion buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ResultadoEvaluacion no encontrado con id " + id));
    }

    private void copiar(ResultadoEvaluacionDTO dto, ResultadoEvaluacion entidad) {
        entidad.setUsuarioId(dto.getUsuarioId());
        entidad.setEvaluacionId(dto.getEvaluacionId());
        entidad.setRespuestasCorrectas(dto.getRespuestasCorrectas());
        entidad.setTotalPreguntas(dto.getTotalPreguntas());
        entidad.setEstado(dto.getEstado() != null ? dto.getEstado() : ResultadoEvaluacion.Estado.PENDIENTE);
        entidad.setFecha(dto.getFecha() != null ? dto.getFecha() : java.time.LocalDateTime.now());

        BigDecimal puntaje = dto.getPuntajePorcentaje();
        if (puntaje == null && dto.getRespuestasCorrectas() != null
                && dto.getTotalPreguntas() != null && dto.getTotalPreguntas() > 0) {
            puntaje = BigDecimal.valueOf(dto.getRespuestasCorrectas())
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(dto.getTotalPreguntas()), 2, RoundingMode.HALF_UP);
        }
        entidad.setPuntajePorcentaje(puntaje);

        Boolean aprobada = dto.getAprobada();
        if (aprobada == null && puntaje != null) {
            aprobada = puntaje.compareTo(NOTA_MINIMA) >= 0;
        }
        entidad.setAprobada(aprobada);
    }

    private ResultadoEvaluacionDTO toDTO(ResultadoEvaluacion e) {
        return new ResultadoEvaluacionDTO(e.getId(), e.getUsuarioId(), e.getEvaluacionId(),
                e.getRespuestasCorrectas(), e.getTotalPreguntas(), e.getPuntajePorcentaje(),
                e.getAprobada(), e.getEstado(), e.getFecha());
    }
}
