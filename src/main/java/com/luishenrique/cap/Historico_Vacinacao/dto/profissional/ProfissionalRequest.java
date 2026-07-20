package com.luishenrique.cap.Historico_Vacinacao.dto.profissional;

import com.luishenrique.cap.Historico_Vacinacao.database.models.ENUM.TipoProfissional;

public record ProfissionalRequest(
        String nome,
        String documento,
        String cargo,
        TipoProfissional tipoProf,
        Long idUnidade
) {}