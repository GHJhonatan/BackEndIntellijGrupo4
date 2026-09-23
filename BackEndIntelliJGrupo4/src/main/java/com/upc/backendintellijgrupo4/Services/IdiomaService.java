package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.IdiomaDTO;
import com.upc.backendintellijgrupo4.Entities.Idioma;
import com.upc.backendintellijgrupo4.Repositories.IdiomaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {

    private final IdiomaRepository idiomaRepository;

    public IdiomaService(IdiomaRepository idiomaRepository) {
        this.idiomaRepository = idiomaRepository;
    }

    public List<Idioma> listar() {
        return idiomaRepository.findAll();
    }

    public Idioma guardar(IdiomaDTO idiomaDTO) {

        Idioma idioma = new Idioma();

        idioma.setNombre(idiomaDTO.getNombre());
        idioma.setDescripcion(idiomaDTO.getDescripcion());
        idioma.setIconoUrl(idiomaDTO.getIconoUrl());

        return idiomaRepository.save(idioma);
    }
}