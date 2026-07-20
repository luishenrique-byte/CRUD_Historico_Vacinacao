package com.luishenrique.cap.Historico_Vacinacao.database.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade_atendimento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeAtendimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    private String rua;

    private Integer numero;

    @Column(length = 8, nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_municipio", nullable = false)
    private MunicipioEntity municipio;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean ativo = true;
}
