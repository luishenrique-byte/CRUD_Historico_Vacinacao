package com.luishenrique.cap.Historico_Vacinacao.dto.fabricante;

import jakarta.validation.constraints.NotBlank;

public record FabricanteRequest(
        @NotBlank String nome
) {}
