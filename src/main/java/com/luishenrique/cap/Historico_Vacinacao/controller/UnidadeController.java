package com.luishenrique.cap.Historico_Vacinacao.controller;

import com.luishenrique.cap.Historico_Vacinacao.dto.unidadeAtendimento.UnidadeRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.unidadeAtendimento.UnidadeResponse;
import com.luishenrique.cap.Historico_Vacinacao.service.UnidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/unidade")
public class UnidadeController {

    private final UnidadeService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UnidadeResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UnidadeResponse findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadeResponse save(@RequestBody UnidadeRequest request){
        return service.save(request);
    }

    @PatchMapping("/enable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable Long id){
        service.enable(id);
    }

    @PatchMapping("/disable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable Long id){
        service.disable(id);
    }
}
