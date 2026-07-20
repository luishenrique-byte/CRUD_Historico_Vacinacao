package com.luishenrique.cap.Historico_Vacinacao.dto.unidade;

import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoRequest;

public record UnidadeRequest(
        String nome,
        EnderecoRequest endereco
) {}
