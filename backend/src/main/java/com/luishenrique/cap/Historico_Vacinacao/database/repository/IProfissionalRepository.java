package com.luishenrique.cap.Historico_Vacinacao.database.repository;

import com.luishenrique.cap.Historico_Vacinacao.database.models.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfissionalRepository extends JpaRepository<ProfissionalEntity, Long> {
    List<ProfissionalEntity> findByDocumento(String documento);
}
