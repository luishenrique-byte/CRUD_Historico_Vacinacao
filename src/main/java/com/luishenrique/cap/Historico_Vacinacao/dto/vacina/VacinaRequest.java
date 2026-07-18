package com.luishenrique.cap.Historico_Vacinacao.dto.vacina;

public record VacinaRequest(
        String nome,
        Integer intervaloDoses,
        Integer idFabricante
) {}
