package com.luishenrique.cap.Historico_Vacinacao.dto.municipio;


import com.luishenrique.cap.Historico_Vacinacao.dto.estado.EstadoResponse;

public record MunicipioResponse(
        Long id,
        String nome,
        Boolean ativo,
        EstadoResponse estado
) {}
