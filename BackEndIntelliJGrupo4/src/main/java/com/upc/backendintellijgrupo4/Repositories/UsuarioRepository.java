package com.upc.backendintellijgrupo4.Repositories;

import com.upc.backendintellijgrupo4.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}