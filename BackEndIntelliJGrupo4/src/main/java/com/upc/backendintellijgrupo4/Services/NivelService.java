package com.upc.backendintellijgrupo4.Services;

import com.upc.backendintellijgrupo4.DTOs.NivelDTO;
import com.upc.backendintellijgrupo4.Entities.Nivel;
import com.upc.backendintellijgrupo4.Repositories.NivelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NivelService {

    private final NivelRepository nivelRepository;

    public NivelService(NivelRepository nivelRepository) {
        this.nivelRepository = nivelRepository;
    }

    public List<Nivel> listar() {
        return nivelRepository.findAll();
    }

    public Nivel guardar(NivelDTO nivelDTO) {

        Nivel nivel = new Nivel();

        nivel.setNombre(nivelDTO.getNombre());
        nivel.setOrden(nivelDTO.getOrden());

        return nivelRepository.save(nivel);
    }
}
