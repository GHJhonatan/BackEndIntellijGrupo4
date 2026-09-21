package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.UsuarioDTO;
import com.upc.backendintellijgrupo4.Entities.Rol;
import com.upc.backendintellijgrupo4.Entities.Usuario;
import com.upc.backendintellijgrupo4.Repositories.RolRepository;
import com.upc.backendintellijgrupo4.Repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();

        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setPasswordHash(usuarioDTO.getPasswordHash());
        usuario.setEstado(usuarioDTO.getEstado());

        Rol rol = rolRepository.findById(usuarioDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }
}