package com.luishenrique.cap.Historico_Vacinacao.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fabricante")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FabricanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @OneToMany(mappedBy = "fabricante")
    private Set<VacinaEntity> vacinas = new HashSet<>();
}
