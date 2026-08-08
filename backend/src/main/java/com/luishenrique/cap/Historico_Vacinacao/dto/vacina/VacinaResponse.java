package com.luishenrique.cap.Historico_Vacinacao.dto.vacina;

import com.luishenrique.cap.Historico_Vacinacao.dto.fabricante.FabricanteResponse;

public record VacinaResponse(
        Integer id,
        String nome,
        Integer intervaloDoses,
        Boolean ativo,
        FabricanteResponse Fabricante
) {}
