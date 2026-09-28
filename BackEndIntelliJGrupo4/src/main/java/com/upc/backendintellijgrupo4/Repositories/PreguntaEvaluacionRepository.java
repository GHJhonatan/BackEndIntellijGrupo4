package com.upc.backendintellijgrupo4.Repositories;

import com.upc.backendintellijgrupo4.Entities.PreguntaEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaEvaluacionRepository extends JpaRepository<PreguntaEvaluacion, Long> {
}