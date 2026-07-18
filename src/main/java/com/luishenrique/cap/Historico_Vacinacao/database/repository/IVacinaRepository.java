package com.luishenrique.cap.Historico_Vacinacao.database.repository;

import com.luishenrique.cap.Historico_Vacinacao.database.models.VacinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVacinaRepository extends JpaRepository<VacinaEntity, Integer> {
}
