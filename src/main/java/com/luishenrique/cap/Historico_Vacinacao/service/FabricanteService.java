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
                        m -> new FabricanteResponse(
                                m.getId(),
                                m.getNome(),
                                m.getAtivo()
                        )
                )
                .toList();

    }

    public FabricanteResponse findById(Integer id){

        FabricanteEntity fabricante = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fabricante não encontrado."));

        return new FabricanteResponse(
                fabricante.getId(),
                fabricante.getNome(),
                fabricante.getAtivo()
        );

    }

    public FabricanteResponse save(FabricanteRequest request){

        FabricanteEntity fabricanteCreated = repository.save(
                FabricanteEntity.builder()
                        .nome(request.nome())
                        .build()
        );

        return new FabricanteResponse(
                fabricanteCreated.getId(),
                fabricanteCreated.getNome(),
                fabricanteCreated.getAtivo()
        );
    }

    public void enable(Integer id){

        FabricanteEntity fabricante = repository.findById(id)
                                    .orElseThrow(() -> new NotFoundException("Fabricante não encontrado"));

        if (!fabricante.getAtivo()){
            fabricante.setAtivo(true);
        } else {
            throw new BadRequestException("Fabricante já está Ativo.");
        }
    }

    public void disable(Integer id){

        FabricanteEntity fabricante = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fabricante não encontrado"));

        if (fabricante.getAtivo()){
            fabricante.setAtivo(false);
        } else {
            throw new BadRequestException("Fabricante já está Inativo.");
        }

    }
}