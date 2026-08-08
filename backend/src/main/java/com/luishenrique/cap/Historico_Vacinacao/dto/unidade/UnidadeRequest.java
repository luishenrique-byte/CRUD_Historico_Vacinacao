package com.luishenrique.cap.Historico_Vacinacao.dto.unidade;

import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UnidadeRequest(
        @NotBlank String nome,
        @NotNull @Valid EnderecoRequest endereco
) {}
