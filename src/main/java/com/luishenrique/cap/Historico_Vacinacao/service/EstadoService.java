package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.EstadoEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IEstadoRepository;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final IEstadoRepository repository;

    public List<EstadoEntity> findAll(){
        return repository.findAll();
    }

    public EstadoEntity findById(Integer id) throws NotFoundException {

        EstadoEntity estado = repository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Estado não encotrado"));

        return estado;
    }
}
