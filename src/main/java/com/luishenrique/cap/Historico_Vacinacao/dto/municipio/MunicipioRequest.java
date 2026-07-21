package com.luishenrique.cap.Historico_Vacinacao.dto.municipio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MunicipioRequest (
        @NotBlank String nome,
        @NotNull Integer idEstado
){}
