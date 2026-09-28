package com.upc.backendintellijgrupo4.Repositories;

import com.upc.backendintellijgrupo4.Entities.OpcionPregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcionPreguntaRepository extends JpaRepository<OpcionPregunta, Long> {
}