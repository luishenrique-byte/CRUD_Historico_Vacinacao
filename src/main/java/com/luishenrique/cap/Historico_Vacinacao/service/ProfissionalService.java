package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.ProfissionalEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.models.UnidadeAtendimentoEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IProfissionalRepository;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IUnidadeRepository;
import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.municipio.MunicipioResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.profissional.ProfissionalRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.profissional.ProfissionalResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.unidade.UnidadeResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final IProfissionalRepository repository;
    private final IUnidadeRepository unidadeRepository;

    public List<ProfissionalResponse> findAll(){
        return repository.findAll().stream()
                .map(m -> toResponse(m))
                .toList();
    }

    public ProfissionalResponse findById(Long id){
        ProfissionalEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não há nenhum Profissional com esse Código. Verifique o ID informado!"));

        return toResponse(entity);
    }

    public ProfissionalResponse save(ProfissionalRequest request){
        UnidadeAtendimentoEntity unidade = unidadeRepository.findById(request.idUnidade())
                .orElseThrow(() -> new NotFoundException("Não nenhuma Unidade de Atendimento com esse Código. Verifique o ID informado!"));

        if (!unidade.getAtivo()){
            throw new BadRequestException("A Unidade de Atendimento informada está INATIVA. Por favor, Ative a Unidade de atendimento ou informe outra Unidade de Atendimento que esteja ATIVA.");
        }

        ProfissionalEntity profissional = repository.save(
                ProfissionalEntity.builder()
                    .nome(request.nome())
                    .documento(request.documento())
                    .cargo(request.cargo())
                    .tipoProf(request.tipoProf())
                    .unidadeAtendimento(unidade)
                .build());

        return toResponse(profissional);
    }

    public void enable(Long id){

        ProfissionalEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhum Profissional encontrado a partir desse CÓD: "+id));

        if (!entity.getAtivo()){
            entity.setAtivo(true);
        } else {
            throw new BadRequestException("Este Profissional(Cód: "+ id +") já está Ativo");
        }

        repository.save(entity);
    }

    public void disable(Long id){

        ProfissionalEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhum Profissional encontrado a partir desse CÓD: "+id));

        if (entity.getAtivo()){
            entity.setAtivo(false);
        } else {
            throw new BadRequestException("Este Profissional(Cód: "+ id +") já está Inativo");
        }

        repository.save(entity);
    }

    private ProfissionalResponse toResponse(ProfissionalEntity entity){
        ProfissionalResponse response = new ProfissionalResponse(
                entity.getId(),
                entity.getNome(),
                entity.getDocumento(),
                entity.getCargo(),
                entity.getTipoProf(),
                entity.getAtivo(),
                new UnidadeResponse(
                        entity.getUnidadeAtendimento().getId(),
                        entity.getUnidadeAtendimento().getNome(),
                        entity.getUnidadeAtendimento().getAtivo(),
                        new EnderecoResponse(
                                entity.getUnidadeAtendimento().getRua(),
                                entity.getUnidadeAtendimento().getNumero(),
                                entity.getUnidadeAtendimento().getCep(),
                                new MunicipioResponse(
                                        entity.getUnidadeAtendimento().getMunicipio().getId(),
                                        entity.getUnidadeAtendimento().getMunicipio().getNome(),
                                        entity.getUnidadeAtendimento().getMunicipio().getAtivo(),
                                        new EstadoResponse(
                                                entity.getUnidadeAtendimento().getMunicipio().getEstado().getId(),
                                                entity.getUnidadeAtendimento().getMunicipio().getEstado().getNome(),
                                                entity.getUnidadeAtendimento().getMunicipio().getEstado().getAtivo()
                                        )
                                )
                        )
                )
        );

        return response;
    }
}
