package com.luishenrique.cap.Historico_Vacinacao.controller;

import com.luishenrique.cap.Historico_Vacinacao.dto.paciente.PacienteRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.paciente.PacienteResponse;
import com.luishenrique.cap.Historico_Vacinacao.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteResponse findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteResponse> findByCpf(@RequestParam String cpf){
        return service.findByCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse save(@RequestBody PacienteRequest request){
        return service.save(request);
    }
}
