package com.luishenrique.cap.Historico_Vacinacao.dto.registro;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegistroRequest(
        @NotBlank String lote,
        @PastOrPresent @NotNull LocalDate dataVacinacao,
        @Past @NotNull LocalDate dataFabricacao,
        @NotNull LocalDate validade,
        @NotNull Long idPaciente,
        @NotNull Long idProfissional,
        @NotNull Long idUnidade,
        @NotNull Integer idVacina
){}
