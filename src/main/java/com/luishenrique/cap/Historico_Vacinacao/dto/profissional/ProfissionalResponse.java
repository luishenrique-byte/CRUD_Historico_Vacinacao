package com.luishenrique.cap.Historico_Vacinacao.dto.profissional;

import com.luishenrique.cap.Historico_Vacinacao.database.models.ENUM.TipoProfissional;
import com.luishenrique.cap.Historico_Vacinacao.dto.unidade.UnidadeResponse;

public record ProfissionalResponse(
   Long id,
   String nome,
   String documento,
   String cargo,
   TipoProfissional tipoProf,
   Boolean ativo,
   UnidadeResponse unidade
) {}
