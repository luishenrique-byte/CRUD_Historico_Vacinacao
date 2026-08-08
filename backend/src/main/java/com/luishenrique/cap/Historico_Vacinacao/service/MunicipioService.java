package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.EstadoEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.models.MunicipioEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IEstadoRepository;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IMunicipioRepository;
import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.municipio.MunicipioRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.municipio.MunicipioResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private final IMunicipioRepository repository;
    private final IEstadoRepository estadoRepository;

    public List<MunicipioResponse> findAll(){
        return repository.findAll().stream()
                .map(m -> toResponse(m)
                )
                .toList();
    }

    public MunicipioResponse findById(Long id){
        //Verifica se existe o estado informado
        MunicipioEntity municipio = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Município não encontrado"));

        //Retorna o Objeto encontrado
        return toResponse(municipio);
    }

    public MunicipioResponse save(MunicipioRequest request){
        //Verifica se existe o estado informado
        EstadoEntity estado = estadoRepository.findById(request.idEstado())
                .orElseThrow(() -> new BadRequestException("Nenhum Estado encontrado com esse código."));

        //Verifica se o Estado está ativo
        if (!estado.getAtivo()){
            throw new BadRequestException("Este Estado está desativado, por favor o ative para poder vincular");
        }

        //Persiste a nova tupla no banco
        MunicipioEntity municipioCreated = repository.save(
                MunicipioEntity.builder()
                        .nome(request.nome())
                        .estado(estado)
                .build()
        );

        //Retorna o objeto criado
        return toResponse(municipioCreated);
    }

    public void enable(Long id){

        //Verifica se existe o Município
        MunicipioEntity municipio = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Município não encontrado"));

        //Lógica para ativar
        if (!municipio.getAtivo()){
            municipio.setAtivo(true);
        } else {
            throw new BadRequestException("Município já está Ativo");
        }

        //Persiste no banco
        repository.save(municipio);

    }

    public void disable(Long id){

        //Verifica se existe o Município
        MunicipioEntity municipio = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Município não encontrado"));

        //Lógica para ativar
        if (municipio.getAtivo()){
            municipio.setAtivo(false);
        } else {
            throw new BadRequestException("Município já está Inativo");
        }

        //Persiste no banco
        repository.save(municipio);

    }

    private MunicipioResponse toResponse(MunicipioEntity entity){

        MunicipioResponse response = new MunicipioResponse(
                entity.getId(),
                entity.getNome(),
                entity.getAtivo(),
                new EstadoResponse(
                        entity.getEstado().getId(),
                        entity.getEstado().getNome(),
                        entity.getEstado().getAtivo()
                )
        );

        return response;

    }
}