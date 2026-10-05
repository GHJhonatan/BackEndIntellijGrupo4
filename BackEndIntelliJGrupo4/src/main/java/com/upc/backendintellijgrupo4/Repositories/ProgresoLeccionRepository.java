package com.upc.backendintellijgrupo4.Repositories;

import com.upc.backendintellijgrupo4.Entities.ProgresoLeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgresoLeccionRepository extends JpaRepository<ProgresoLeccion, Long> {
    List<ProgresoLeccion> findByUsuarioId(Long usuarioId);

    Optional<ProgresoLeccion> findByUsuarioIdAndLeccionId(Long usuarioId, Long leccionId);
}
