package com.luishenrique.cap.Historico_Vacinacao.database.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vacina")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VacinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "intervalo_doses")
    private Integer intervaloDoses;

    @ManyToOne
    @JoinColumn(name = "id_fabricante", nullable = false)
    private FabricanteEntity fabricante;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean ativo = true;
}
