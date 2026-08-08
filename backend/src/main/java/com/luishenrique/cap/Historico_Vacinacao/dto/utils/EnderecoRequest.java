package com.luishenrique.cap.Historico_Vacinacao.dto.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRequest(
        @NotBlank String rua,
        @NotNull Integer numero,
        @NotBlank String cep,
        @NotNull Long idMunicipio
) {}
