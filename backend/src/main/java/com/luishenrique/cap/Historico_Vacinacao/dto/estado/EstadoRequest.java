package com.luishenrique.cap.Historico_Vacinacao.dto.estado;

import jakarta.validation.constraints.NotBlank;

public record EstadoRequest(
        @NotBlank String nome
) {}
