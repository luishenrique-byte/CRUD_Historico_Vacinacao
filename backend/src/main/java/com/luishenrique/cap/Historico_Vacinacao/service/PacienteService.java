package com.luishenrique.cap.Historico_Vacinacao.service;

import com.luishenrique.cap.Historico_Vacinacao.database.models.MunicipioEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.models.PacienteEntity;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IMunicipioRepository;
import com.luishenrique.cap.Historico_Vacinacao.database.repository.IPacienteRepository;
import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.municipio.MunicipioResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.paciente.PacienteRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.paciente.PacienteResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoResponse;
import com.luishenrique.cap.Historico_Vacinacao.exception.BadRequestException;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
import com.luishenrique.cap.Historico_Vacinacao.utils.CpfUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final IPacienteRepository repository;
    private final IMunicipioRepository municipioRepository;

    // Remove qualquer caractere que não seja dígito (pontos, traços, espaços)
    private static final String REGEX_REMOVE_NAO_NUMEROS = "[^0-9]";

    public List<PacienteResponse> findAll(){
        return repository.findAll().stream()
                .map(m -> toResponse(m))
                .toList();
    }

    public PacienteResponse findById(Long id){
        PacienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado por esse CÓD"));

        return toResponse(entity);
    }

    public List<PacienteResponse> findByCpf(String cpf){

        if (!CpfUtils.isValid(cpf)) throw new BadRequestException("O CPF: " + cpf + " Não é valido, verifique se o formato ou sequência está correta informado. Formatos aceitos: (000.000.000-00 ou 11111111111)");

        cpf = cpf.replaceAll(REGEX_REMOVE_NAO_NUMEROS,"");

        List<PacienteEntity> pacienteEntityList = repository.findByCpf(cpf);

        if (pacienteEntityList.isEmpty()){
            throw new NotFoundException("Nenhum paciente encontrado pelo CPF informado");
        }

        return pacienteEntityList.stream()
                .map(m -> toResponse(m))
                .toList();
    }

    public PacienteResponse save(PacienteRequest request){

        if (!CpfUtils.isValid(request.cpf())){
            throw new BadRequestException("O CPF: " + request.cpf() + "Não é valido, verifique se o formato ou sequência está correta informado. Formatos aceitos: (000.000.000-00 ou 11111111111)");
        }

        MunicipioEntity municipio = municipioRepository.findById(request.endereco().idMunicipio())
                .orElseThrow(() -> new BadRequestException("Municipio não existente"));

        PacienteEntity paciente = repository.save(
                PacienteEntity.builder()
                        .nome(request.nome())
                        .dataNascimento(request.dataNascimento())
                        .cpf(request.cpf().replaceAll(REGEX_REMOVE_NAO_NUMEROS,""))
                        .rua(request.endereco().rua())
                        .numero(request.endereco().numero())
                        .cep(request.endereco().cep())
                        .municipio(municipio)
                .build());

        return toResponse(paciente);
    }

    private PacienteResponse toResponse(PacienteEntity entity){
        PacienteResponse response = new PacienteResponse(
                entity.getId(),
                entity.getNome(),
                entity.getDataNascimento(),
                entity.getCpf(),
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
