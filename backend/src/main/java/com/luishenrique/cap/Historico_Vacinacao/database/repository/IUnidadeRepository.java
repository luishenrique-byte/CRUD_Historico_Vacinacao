package com.luishenrique.cap.Historico_Vacinacao.database.repository;

import com.luishenrique.cap.Historico_Vacinacao.database.models.UnidadeAtendimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnidadeRepository extends JpaRepository<UnidadeAtendimentoEntity, Long> {
}
