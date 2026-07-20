package com.luishenrique.cap.Historico_Vacinacao.controller;

import com.luishenrique.cap.Historico_Vacinacao.dto.profissional.ProfissionalRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.profissional.ProfissionalResponse;
import com.luishenrique.cap.Historico_Vacinacao.service.ProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/profissional")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfissionalResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfissionalResponse findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalResponse save(@RequestBody ProfissionalRequest request){
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
