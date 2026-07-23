package com.luishenrique.cap.Historico_Vacinacao.controller;

import com.luishenrique.cap.Historico_Vacinacao.dto.registro.RegistroRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.registro.RegistroResponse;
import com.luishenrique.cap.Historico_Vacinacao.service.RegistroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/registro")
public class RegistroController {

    private final RegistroService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RegistroResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegistroResponse findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroResponse save(@RequestBody @Valid RegistroRequest request){
        return service.save(request);
    }
}
