package com.upc.backendintellijgrupo4.Repositories;

import com.upc.backendintellijgrupo4.Entities.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
}