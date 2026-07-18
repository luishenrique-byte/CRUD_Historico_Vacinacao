package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.FabricanteEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IFabricanteRepository;
import com.luishenrique.cap.Historico_Vacinacao.dto.fabricante.FabricanteRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.fabricante.FabricanteResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FabricanteService {

    private final IFabricanteRepository repository;

    public List<FabricanteResponse> findAll(){

        return repository.findAll()
                .stream()
                .map(
                        m -> toResponse(m)
                )
                .toList();

    }

    public FabricanteResponse findById(Integer id){

        FabricanteEntity fabricante = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fabricante não encontrado."));

        return toResponse(fabricante);

    }

    public FabricanteResponse save(FabricanteRequest request){

        FabricanteEntity fabricanteCreated = repository.save(
                FabricanteEntity.builder()
                        .nome(request.nome())
                        .build()
        );

        return toResponse(fabricanteCreated);
    }

    public void enable(Integer id){

        //Verifica se existe o Município
        FabricanteEntity fabricante = repository.findById(id)
                                    .orElseThrow(() -> new NotFoundException("Fabricante não encontrado"));

        //Lógica para ativar
        if (!fabricante.getAtivo()){
            fabricante.setAtivo(true);
        } else {
            throw new BadRequestException("Fabricante já está Ativo.");
        }

        //Persiste no banco
        repository.save(fabricante);
        
    }

    public void disable(Integer id){

        //Verifica se existe o Município
        FabricanteEntity fabricante = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fabricante não encontrado"));


        //Lógica para Inativar
        if (fabricante.getAtivo()){
            fabricante.setAtivo(false);
        } else {
            throw new BadRequestException("Fabricante já está Inativo.");
        }

        //Persiste no banco
        repository.save(fabricante);

    }

    private FabricanteResponse toResponse(FabricanteEntity entity){
        FabricanteResponse response = new FabricanteResponse(
                entity.getId(),
                entity.getNome(),
                entity.getAtivo()
        );

        return response;
    }
}