package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.LeccionDTO;
import com.upc.backendintellijgrupo4.Entities.Idioma;
import com.upc.backendintellijgrupo4.Entities.Leccion;
import com.upc.backendintellijgrupo4.Entities.Nivel;
import com.upc.backendintellijgrupo4.Repositories.IdiomaRepository;
import com.upc.backendintellijgrupo4.Repositories.LeccionRepository;
import com.upc.backendintellijgrupo4.Repositories.NivelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LeccionService {

    private final LeccionRepository leccionRepository;
    private final IdiomaRepository idiomaRepository;
    private final NivelRepository nivelRepository;

    public LeccionService(LeccionRepository leccionRepository,
                          IdiomaRepository idiomaRepository,
                          NivelRepository nivelRepository) {
        this.leccionRepository = leccionRepository;
        this.idiomaRepository = idiomaRepository;
        this.nivelRepository = nivelRepository;
    }

    public List<Leccion> listar() {
        return leccionRepository.findAll();
    }

    public Leccion guardar(LeccionDTO leccionDTO) {

        Leccion leccion = new Leccion();

        Idioma idioma = idiomaRepository.findById(leccionDTO.getIdiomaId())
                .orElseThrow(() -> new RuntimeException("Idioma no encontrado"));

        Nivel nivel = nivelRepository.findById(leccionDTO.getNivelId())
                .orElseThrow(() -> new RuntimeException("Nivel no encontrado"));

        leccion.setIdioma(idioma);
        leccion.setNivel(nivel);
        leccion.setTitulo(leccionDTO.getTitulo());
        leccion.setDescripcion(leccionDTO.getDescripcion());
        leccion.setTipoContenido(leccionDTO.getTipoContenido());
        leccion.setUrlContenido(leccionDTO.getUrlContenido());
        leccion.setDuracionMin(leccionDTO.getDuracionMin());
        leccion.setOrden(leccionDTO.getOrden());

        return leccionRepository.save(leccion);
    }

}
