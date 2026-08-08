package com.luishenrique.cap.Historico_Vacinacao.dto.vacina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VacinaRequest(
        @NotBlank String nome,
        Integer intervaloDoses,
        @NotNull Integer idFabricante
) {}
