package com.luishenrique.cap.Historico_Vacinacao.controller;

import com.luishenrique.cap.Historico_Vacinacao.database.models.EstadoEntity;
import com.luishenrique.cap.Historico_Vacinacao.exception.NotFoundException;
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
    public List<EstadoEntity> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoEntity findById(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }
}
