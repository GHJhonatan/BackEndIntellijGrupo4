package com.upc.backendintellijgrupo4.Repositories;

import com.upc.backendintellijgrupo4.Entities.Leccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeccionRepository extends JpaRepository<Leccion, Long> {
}
