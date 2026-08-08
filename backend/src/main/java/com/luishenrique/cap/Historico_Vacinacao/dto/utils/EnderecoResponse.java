package com.luishenrique.cap.Historico_Vacinacao.dto.utils;

import com.luishenrique.cap.Historico_Vacinacao.dto.municipio.MunicipioResponse;

public record EnderecoResponse(
        String rua,
        Integer numero,
        String cep,
        MunicipioResponse municipio
) {}
