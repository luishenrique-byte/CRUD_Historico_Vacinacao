package com.luishenrique.cap.Historico_Vacinacao.database.repository;

import com.luishenrique.cap.Historico_Vacinacao.database.models.FabricanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFabricanteRepository extends JpaRepository<FabricanteEntity, Integer> {
}
