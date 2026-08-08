package com.luishenrique.cap.Historico_Vacinacao.dto.paciente;

import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoResponse;

import java.time.LocalDate;

public record PacienteResponse(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        EnderecoResponse endereco
) {}
