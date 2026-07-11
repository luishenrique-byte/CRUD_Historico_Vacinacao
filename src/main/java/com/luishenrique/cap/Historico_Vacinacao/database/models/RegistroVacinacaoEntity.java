package com.luishenrique.cap.Historico_Vacinacao.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "registro_vacinacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistroVacinacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "data_vacinacao", nullable = false)
    private LocalDate dataVacinacao;

    @Column(nullable = false)
    private String lote;

    @Column(name = "data_fabricacao", nullable = false)
    private LocalDate dataFabricacao;

    @Column(nullable = false)
    private LocalDate validade;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "id_profissional", nullable = false)
    private ProfissionalEntity profissional;

    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private UnidadeAtendimentoEntity unidade;

    @ManyToOne
    @JoinColumn(name = "id_vacina", nullable = false)
    private VacinaEntity vacina;
}
