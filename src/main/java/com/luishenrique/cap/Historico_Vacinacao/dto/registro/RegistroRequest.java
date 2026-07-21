package com.luishenrique.cap.Historico_Vacinacao.dto.registro;

import java.time.LocalDate;

public record RegistroRequest(
        String lote,
        LocalDate dataVacinacao,
        LocalDate dataFabricacao,
        LocalDate validade,
        Long idPaciente,
        Long idProfissional,
        Long idUnidade,
        Integer idVacina
){}
