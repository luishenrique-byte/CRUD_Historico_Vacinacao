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
import com.luishenrique.cap.Historico_Vacinacao.utils.CnpjUtils;
import com.luishenrique.cap.Historico_Vacinacao.utils.CpfUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final IProfissionalRepository repository;
    private final IUnidadeRepository unidadeRepository;

    // Remove qualquer caractere que não seja dígito (pontos, traços, espaços)
    private static final String REGEX_REMOVE_NAO_NUMEROS = "[^0-9]";

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

    public List<ProfissionalResponse> findByCpf(String cpf){

        if (!CpfUtils.isValid(cpf)){
            throw new BadRequestException("O CPF: " + cpf + " Não é valido, verifique se o formato ou sequência está correta informado. Formatos aceitos: (000.000.000-00 ou 11111111111)");
        }

        cpf = cpf.replaceAll(REGEX_REMOVE_NAO_NUMEROS,"");

        return repository.findByDocumento(cpf).stream()
                .map( m -> toResponse(m))
                .toList();
    }

    public List<ProfissionalResponse> findByCnpj(String cnpj){

        if (!CnpjUtils.isValid(cnpj)){
            throw new BadRequestException("O CNPJ: " + cnpj + " Não é valido, verifique se o formato ou sequência está correta informado. Formatos aceitos: (12.345.678/0001-90) ou (12345678000190)");
        }

        cnpj = cnpj.replaceAll(REGEX_REMOVE_NAO_NUMEROS, "");

        return repository.findByDocumento(cnpj).stream()
                .map(m -> toResponse(m))
                .toList();
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
                    .unidade(unidade)
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
                        entity.getUnidade().getId(),
                        entity.getUnidade().getNome(),
                        entity.getUnidade().getAtivo(),
                        new EnderecoResponse(
                                entity.getUnidade().getRua(),
                                entity.getUnidade().getNumero(),
                                entity.getUnidade().getCep(),
                                new MunicipioResponse(
                                        entity.getUnidade().getMunicipio().getId(),
                                        entity.getUnidade().getMunicipio().getNome(),
                                        entity.getUnidade().getMunicipio().getAtivo(),
                                        new EstadoResponse(
                                                entity.getUnidade().getMunicipio().getEstado().getId(),
                                                entity.getUnidade().getMunicipio().getEstado().getNome(),
                                                entity.getUnidade().getMunicipio().getEstado().getAtivo()
                                        )
                                )
                        )
                )
        );

        return response;
    }
}
