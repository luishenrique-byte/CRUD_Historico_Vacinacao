package com.luishenrique.cap.Historico_Vacinacao.database.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "municipio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MunicipioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoEntity estado;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean ativo = true;

}
