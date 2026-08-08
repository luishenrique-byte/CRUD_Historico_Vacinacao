package com.luishenrique.cap.Historico_Vacinacao.controller;

import com.luishenrique.cap.Historico_Vacinacao.dto.vacina.VacinaRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.vacina.VacinaResponse;
import com.luishenrique.cap.Historico_Vacinacao.service.VacinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vacina")
@RequiredArgsConstructor
public class VacinaController {

    private final VacinaService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VacinaResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacinaResponse findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacinaResponse save(@RequestBody @Valid VacinaRequest request){
        return service.save(request);
    }

    @PatchMapping("/enable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable Integer id){
        service.enable(id);
    }

    @PatchMapping("/disable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable Integer id){
        service.disable(id);
    }
}
