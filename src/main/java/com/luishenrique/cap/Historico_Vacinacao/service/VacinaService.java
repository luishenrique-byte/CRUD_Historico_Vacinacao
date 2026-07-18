package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.FabricanteEntity;import com.luishenrique.cap.Historico_Vacinacao.database.models.VacinaEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IFabricanteRepository;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IVacinaRepository;
import com.luishenrique.cap.Historico_Vacinacao.dto.fabricante.FabricanteResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.vacina.VacinaRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.vacina.VacinaResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacinaService {

    private final IVacinaRepository repository;
    private final IFabricanteRepository fabricanteRepository;

    public List<VacinaResponse> findAll(){

        return repository.findAll().stream()
                .map( m ->(
                            new VacinaResponse(
                                    m.getId(),
                                    m.getNome(),
                                    m.getIntervaloDoses(),
                                    m.getAtivo(),
                                    new FabricanteResponse(
                                            m.getFabricante().getId(),
                                            m.getFabricante().getNome(),
                                            m.getFabricante().getAtivo()
                                    )
                            )
                        )
                )
                .toList();
    }

    public VacinaResponse findById(Integer id){

        VacinaEntity vac = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Para esse código, não foi encontrado nenhuma Vacina"));

        return new VacinaResponse(
                vac.getId(),
                vac.getNome(),
                vac.getIntervaloDoses(),
                vac.getAtivo(),
                new FabricanteResponse(
                        vac.getFabricante().getId(),
                        vac.getFabricante().getNome(),
                        vac.getFabricante().getAtivo()
                )
        );
    }

    public VacinaResponse save(VacinaRequest request){


        FabricanteEntity fabricante = fabricanteRepository.findById(request.idFabricante())
                .orElseThrow(() -> new BadRequestException("Não há fabricante pertecente desse código."));

        if (!fabricante.getAtivo()){
            throw new BadRequestException("Não poderá vincular a esse fabricante pois se encontra Inativo.\n" +
                    "Sujestão: ativo o fabricante(CÓD: " + fabricante.getId()+")");
        }

        VacinaEntity vac = repository.save(VacinaEntity.builder()
                        .nome(request.nome())
                        .intervaloDoses(request.intervaloDoses())
                        .fabricante(fabricante)
                .build());

        return new VacinaResponse(
                vac.getId(),
                vac.getNome(),
                vac.getIntervaloDoses(),
                vac.getAtivo(),
                new FabricanteResponse(
                        vac.getFabricante().getId(),
                        vac.getFabricante().getNome(),
                        vac.getFabricante().getAtivo()
                )
        );

    }

    public void enable(Integer id){

        //Verifica se existe o Vacina
        VacinaEntity vacina = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vacina não encontrado"));

        //Lógica para ativar
        if (!vacina.getAtivo()){
            vacina.setAtivo(true);
        } else {
            throw new BadRequestException("Vacina já está Ativo");
        }

        //Persiste no banco
        repository.save(vacina);

    }
    public void disable(Integer id){

        //Verifica se existe o Vacina
        VacinaEntity vacina = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vacina não encontrado"));

        //Lógica para inativar
        if (vacina.getAtivo()){
            vacina.setAtivo(false);
        } else {
            throw new BadRequestException("Vacina já está Inativo");
        }

        //Persiste no banco
        repository.save(vacina);

    }
}
