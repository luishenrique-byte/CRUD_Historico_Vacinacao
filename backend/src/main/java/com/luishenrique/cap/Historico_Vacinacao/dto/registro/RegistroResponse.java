package com.luishenrique.cap.Historico_Vacinacao.dto.registro;

import com.luishenrique.cap.Historico_Vacinacao.dto.paciente.PacienteResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.profissional.ProfissionalResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.unidade.UnidadeResponse;
import com.luishenrique.cap.Historico_Vacinacao.dto.vacina.VacinaResponse;

import java.time.LocalDate;

public record RegistroResponse(
        Long id,
        LocalDate dataVacinacao,
        String lote,
        LocalDate dataFabricacao,
        LocalDate validade,
        VacinaResponse vacina,
        PacienteResponse paciente,
        ProfissionalResponse profissional,
        UnidadeResponse unidade
) {}
