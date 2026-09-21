package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.RolDTO;
import com.upc.backendintellijgrupo4.Entities.Rol;
import com.upc.backendintellijgrupo4.Repositories.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    public Rol guardar(RolDTO rolDTO) {

        Rol rol = new Rol();

        rol.setNombre(rolDTO.getNombre());
        rol.setDescripcion(rolDTO.getDescripcion());

        return rolRepository.save(rol);
    }
}