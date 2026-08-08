package com.luishenrique.cap.Historico_Vacinacao.controller;

import com.luishenrique.cap.Historico_Vacinacao.dto.fabricante.FabricanteRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.fabricante.FabricanteResponse;
import com.luishenrique.cap.Historico_Vacinacao.service.FabricanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/fabricante")
@RequiredArgsConstructor
public class FabricanteController {

    private final FabricanteService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FabricanteResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public FabricanteResponse findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FabricanteResponse save(@RequestBody @Valid FabricanteRequest request) {
        return service.save(request);
    }

    @PatchMapping("/enable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable Integer id){
        service.enable(id);
    }

    @PatchMapping("/disable({id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable Integer id){
        service.disable(id);
    }
}
