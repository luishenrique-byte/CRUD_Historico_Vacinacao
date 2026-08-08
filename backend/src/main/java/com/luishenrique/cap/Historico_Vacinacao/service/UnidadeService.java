package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.MunicipioEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.models.UnidadeAtendimentoEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IMunicipioRepository;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IUnidadeRepository;
import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.municipio.MunicipioResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.unidade.UnidadeRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.unidade.UnidadeResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final IUnidadeRepository repository;
    private final IMunicipioRepository municipioRepository;

    public List<UnidadeResponse> findAll(){

        return repository.findAll().stream()
                .map(m -> toResponse(m))
                .toList();

    }

    public UnidadeResponse findById(Long id){

        UnidadeAtendimentoEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unidade Atendimento não encontrado a partir desse Código"));

        return toResponse(entity);
    }

    public UnidadeResponse save(UnidadeRequest request){

        MunicipioEntity municipio = municipioRepository.findById(request.endereco().idMunicipio())
                .orElseThrow(() -> new BadRequestException("Não há municipio com Código informado!"));

        UnidadeAtendimentoEntity entity = repository.save(
                UnidadeAtendimentoEntity
                        .builder()
                        .nome(request.nome())
                        .rua(request.endereco().rua())
                        .numero(request.endereco().numero())
                        .cep(request.endereco().cep())
                        .municipio(municipio)
                        .build()
        );

        return toResponse(entity);
    }

    public void enable(Long id){

        UnidadeAtendimentoEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unidade de Atendimento não encontrado. Verifique o Código informado."));

        if (!entity.getAtivo()){
            entity.setAtivo(true);
        } else {
            throw new BadRequestException("Unidade de Atendimento já está Ativa");
        }

        repository.save(entity);

    }

    public void disable(Long id){

        UnidadeAtendimentoEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unidade de Atendimento não encontrado. Verifique o Código informado."));

        if (entity.getAtivo()){
            entity.setAtivo(false);
        } else {
            throw new BadRequestException("Unidade de Atendimento já está Inativa");
        }

        repository.save(entity);

    }

    private UnidadeResponse toResponse(UnidadeAtendimentoEntity entity){
        UnidadeResponse response = new UnidadeResponse(
                entity.getId(),
                entity.getNome(),
                entity.getAtivo(),
                new EnderecoResponse(
                        entity.getRua(),
                        entity.getNumero(),
                        entity.getCep(),
                        new MunicipioResponse(
                                entity.getMunicipio().getId(),
                                entity.getMunicipio().getNome(),
                                entity.getMunicipio().getAtivo(),
                                new EstadoResponse(
                                        entity.getMunicipio().getEstado().getId(),
                                        entity.getMunicipio().getEstado().getNome(),
                                        entity.getMunicipio().getEstado().getAtivo()
                                )
                        )
                )
        );

        return response;
    }
}