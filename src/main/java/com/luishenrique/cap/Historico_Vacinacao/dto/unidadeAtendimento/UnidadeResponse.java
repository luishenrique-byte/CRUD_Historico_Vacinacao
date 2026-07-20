package com.luishenrique.cap.Historico_Vacinacao.dto.unidadeAtendimento;

import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoResponse;

public record UnidadeResponse(
        Long id,
        String nome,
        Boolean ativo,
        EnderecoResponse endereco
){}
