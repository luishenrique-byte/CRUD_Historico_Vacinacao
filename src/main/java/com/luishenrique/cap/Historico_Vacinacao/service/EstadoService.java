package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.EstadoEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IEstadoRepository;
import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final IEstadoRepository repository;

    public List<EstadoResponse> findAll(){
        return repository.findAll()
                .stream()
                .map( m -> toResponse(m)
                )
                .toList();
    }

    public EstadoResponse findById(Integer id) {

        EstadoEntity estado = repository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Estado não encotrado"));

        return toResponse(estado);
    }

    public void save(EstadoRequest request){
        repository.save(
                EstadoEntity
                        .builder()
                        .nome(request.nome())
                        .build()
        );
    }

    public void disable(Integer id){
        EstadoEntity estado = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado não encontrado"));

        if (estado.getAtivo()){
            estado.setAtivo(false);
        } else {
            throw new BadRequestException("Estado já está Inativo");
        }

        repository.save(estado);
    }

    public void enable(Integer id){
        EstadoEntity estado = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado não encontrado"));

        if (!estado.getAtivo()){
            estado.setAtivo(true);
        } else {
            throw new BadRequestException("Estado já está Ativo");
        }

        repository.save(estado);
    }

    private EstadoResponse toResponse(EstadoEntity entity){
        EstadoResponse response = new EstadoResponse(
                entity.getId(),
                entity.getNome(),
                entity.getAtivo()
        );

        return response;
    }
}
