package com.luishenrique.cap.Historico_Vacinacao.dto.paciente;

import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoRequest;

import java.time.LocalDate;

public record PacienteRequest (
    String nome,
    LocalDate dataNascimento,
    String cpf,
    EnderecoRequest endereco
){}
