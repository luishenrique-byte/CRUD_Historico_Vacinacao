package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.*;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.*;
import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.fabricante.FabricanteResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.municipio.MunicipioResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.paciente.PacienteResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.profissional.ProfissionalResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.registro.RegistroRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.registro.RegistroResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.unidade.UnidadeResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.vacina.VacinaResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final IRegistroRepository repository;
    private final IVacinaRepository vacinaRepository;
    private final IPacienteRepository pacienteRepository;
    private final IProfissionalRepository profissionalRepository;
    private final IUnidadeRepository unidadeRepository;

    public List<RegistroResponse> findAll(){
        return repository.findAll().stream()
                .map(m -> toResponse(m))
                .toList();
    }

    public List<RegistroResponse> findAllByPaciente(Long idPaciente){
        PacienteEntity pac = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new NotFoundException("Não há Paciente com esse Código. Verifique o Código Informado."));

        return repository.findAllByPaciente(pac).stream()
                .map(m -> toResponse(m))
                .toList();
    }

    public RegistroResponse findById(Long id){
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não há Registro de Vacinação com esse Código. Verifique o Código Informado.")));
    }

    public RegistroResponse save(RegistroRequest request){

        //Busca pela Vacina informada
        VacinaEntity vacinaRequest = vacinaRepository.findById(request.idVacina())
                .orElseThrow(() -> new NotFoundException("Não há Vacina com o Código informado."));

        //Valida se a Vacina está Ativa
        if (!vacinaRequest.getAtivo()){
            throw new BadRequestException("A Vacina informada está INATIVA. Por favor, Ative a Vacina(CÓD: "+ request.idVacina()+") ou informe outra Vacina que esteja ATIVA.");
        }

        //Busca pelo Paciente
        PacienteEntity pacienteRequest = pacienteRepository.findById(request.idPaciente())
                .orElseThrow(() -> new NotFoundException("Não foi encontrado Paciente com o Código informado."));

        //Busca pelo Profissional
        ProfissionalEntity profissionalRequest = profissionalRepository.findById(request.idProfissional())
                .orElseThrow(() -> new NotFoundException("Não foi encontrado Profissional com o Código informado"));

        //Valida se o profissional está ativo no sistema
        if (!profissionalRequest.getAtivo()){
            throw new BadRequestException("O Profissional informado está INATIVO. Por favor, Ative o Profissional(CÓD: "+ request.idProfissional()+") ou informe outro Profissional que esteja ATIVO.");
        }

        //Busca pela Unidade de Atendimento
        UnidadeAtendimentoEntity unidadeRequest = unidadeRepository.findById(request.idUnidade())
                .orElseThrow(() -> new NotFoundException("Não foi encontrado Unidade de Atendimento com o Código informado."));

        //Valida se a Unidade informada está ativa
        if (!unidadeRequest.getAtivo()){
            throw new BadRequestException("A Unidade de Atendimento informada está INATIVA. Por favor, Ative a Unidade de atendimento ou informe outra Unidade de Atendimento que esteja ATIVA.");
        }

        //Traz o registro salvo no banco
        RegistroVacinacaoEntity registro = repository.save(
                RegistroVacinacaoEntity.builder()
                        .dataVacinacao(request.dataVacinacao())
                        .lote(request.lote())
                        .dataFabricacao(request.dataFabricacao())
                        .validade(request.validade())
                        .vacina(vacinaRequest)
                        .paciente(pacienteRequest)
                        .profissional(profissionalRequest)
                        .unidade(unidadeRequest)
                    .build()
        );

        //Retorna o registro mapeado como response
        return toResponse(registro);
    }

    private RegistroResponse toResponse(RegistroVacinacaoEntity entity){
        RegistroResponse response = new RegistroResponse(
                entity.getId(),
                entity.getDataVacinacao(),
                entity.getLote(),
                entity.getDataFabricacao(),
                entity.getValidade(),
                new VacinaResponse(
                        entity.getVacina().getId(),
                        entity.getVacina().getNome(),
                        entity.getVacina().getIntervaloDoses(),
                        entity.getVacina().getAtivo(),
                        new FabricanteResponse(
                                entity.getVacina().getFabricante().getId(),
                                entity.getVacina().getFabricante().getNome(),
                                entity.getVacina().getFabricante().getAtivo()
                        )
                ),
                new PacienteResponse(
                        entity.getPaciente().getId(),
                        entity.getPaciente().getNome(),
                        entity.getPaciente().getDataNascimento(),
                        entity.getPaciente().getCpf(),
                        new EnderecoResponse(
                                entity.getPaciente().getRua(),
                                entity.getPaciente().getNumero(),
                                entity.getPaciente().getCep(),
                                new MunicipioResponse(
                                        entity.getPaciente().getMunicipio().getId(),
                                        entity.getPaciente().getMunicipio().getNome(),
                                        entity.getPaciente().getMunicipio().getAtivo(),
                                        new EstadoResponse(
                                                entity.getPaciente().getMunicipio().getEstado().getId(),
                                                entity.getPaciente().getMunicipio().getEstado().getNome(),
                                                entity.getPaciente().getMunicipio().getEstado().getAtivo()
                                        )
                                )
                        )
                ),
                new ProfissionalResponse(
                        entity.getProfissional().getId(),
                        entity.getProfissional().getNome(),
                        entity.getProfissional().getDocumento(),
                        entity.getProfissional().getCargo(),
                        entity.getProfissional().getTipoProf(),
                        entity.getProfissional().getAtivo(),
                        new UnidadeResponse(
                                entity.getProfissional().getUnidade().getId(),
                                entity.getProfissional().getUnidade().getNome(),
                                entity.getProfissional().getUnidade().getAtivo(),
                                new EnderecoResponse(
                                        entity.getProfissional().getUnidade().getRua(),
                                        entity.getProfissional().getUnidade().getNumero(),
                                        entity.getProfissional().getUnidade().getCep(),
                                        new MunicipioResponse(
                                                entity.getProfissional().getUnidade().getMunicipio().getId(),
                                                entity.getProfissional().getUnidade().getMunicipio().getNome(),
                                                entity.getProfissional().getUnidade().getMunicipio().getAtivo(),
                                                new EstadoResponse(
                                                        entity.getProfissional().getUnidade().getMunicipio().getEstado().getId(),
                                                        entity.getProfissional().getUnidade().getMunicipio().getEstado().getNome(),
                                                        entity.getProfissional().getUnidade().getMunicipio().getEstado().getAtivo()
                                                )
                                        )
                                )
                        )
                ),
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