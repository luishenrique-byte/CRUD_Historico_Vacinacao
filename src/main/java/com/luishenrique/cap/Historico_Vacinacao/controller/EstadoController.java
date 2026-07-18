package com.luishenrique.cap.Historico_Vacinacao.controller;

import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoRequest;
import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoResponse;
import com.luishenrique.cap.Historico_Vacinacao.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/estado")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EstadoResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoResponse save(@RequestBody EstadoRequest request){
        return service.save(request);
    }

    @PatchMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable Integer id){
        service.disable(id);
    }

    @PatchMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable Integer id){
        service.enable(id);
    }
}
