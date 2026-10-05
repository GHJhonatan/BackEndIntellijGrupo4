package com.upc.backendintellijgrupo4.Repositories;

import com.upc.backendintellijgrupo4.Entities.ResultadoEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoEvaluacionRepository extends JpaRepository<ResultadoEvaluacion, Long> {
    List<ResultadoEvaluacion> findByUsuarioId(Long usuarioId);

    List<ResultadoEvaluacion> findByEvaluacionId(Long evaluacionId);
}
