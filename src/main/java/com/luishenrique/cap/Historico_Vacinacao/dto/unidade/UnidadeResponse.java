package com.luishenrique.cap.Historico_Vacinacao.dto.unidade;

import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoResponse;

public record UnidadeResponse(
        Long id,
        String nome,
        Boolean ativo,
        EnderecoResponse endereco
){}
