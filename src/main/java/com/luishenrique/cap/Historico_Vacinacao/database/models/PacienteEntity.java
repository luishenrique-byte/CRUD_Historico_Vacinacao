package com.luishenrique.cap.Historico_Vacinacao.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento" ,nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 11)
    private String cpf;

    private String rua;

    private Integer numero;

    @Column(length = 8, nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_municipio", nullable = false)
    private MunicipioEntity municipio;

    @Column(name = "ativo", columnDefinition = "boolean default true")
    private Boolean ativo = true;
}
