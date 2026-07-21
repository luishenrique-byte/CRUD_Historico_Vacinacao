package com.luishenrique.cap.Historico_Vacinacao.dto.paciente;

import com.luishenrique.cap.Historico_Vacinacao.dto.utils.EnderecoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PacienteRequest (
    @NotBlank String nome,
    @PastOrPresent LocalDate dataNascimento,
    @CPF @NotBlank String cpf,
    @NotNull @Valid EnderecoRequest endereco
){}
