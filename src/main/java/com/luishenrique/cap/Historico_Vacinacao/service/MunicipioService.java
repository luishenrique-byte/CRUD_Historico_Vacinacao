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
                .map(m -> new MunicipioResponse(
                        m.getId(),
                        m.getNome(),
                        m.getAtivo(),
                        new EstadoResponse(
                            m.getEstado().getId(),
                            m.getEstado().getNome(),
                            m.getEstado().getAtivo()
                        )
                    )
                )
                .toList();
    }

    public MunicipioResponse findById(Long id){
        //Verifica se existe o estado informado
        MunicipioEntity municipio = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Município não encontrado"));

        //Retorna o Objeto encontrado
        return new MunicipioResponse(
                municipio.getId(),
                municipio.getNome(),
                municipio.getAtivo(),
                new EstadoResponse(
                        municipio.getEstado().getId(),
                        municipio.getEstado().getNome(),
                        municipio.getEstado().getAtivo()
                )
        );
    }

    public MunicipioResponse save(MunicipioRequest request){
        //Verifica se existe o estado informado
        EstadoEntity estado = estadoRepository.findById(request.idEstado())
                .orElseThrow(() -> new NotFoundException("Estado não encontrado"));

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
        return new MunicipioResponse(
                municipioCreated.getId(),
                municipioCreated.getNome(),
                municipioCreated.getAtivo(),
                new EstadoResponse(
                        municipioCreated.getEstado().getId(),
                        municipioCreated.getEstado().getNome(),
                        municipioCreated.getEstado().getAtivo()
                )
        );
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
}