package com.luishenrique.cap.Historico_Vacinacao.dto.utils;

public record EnderecoRequest(
        String rua,
        Integer numero,
        String cep,
        Long idMunicipio
) {}
