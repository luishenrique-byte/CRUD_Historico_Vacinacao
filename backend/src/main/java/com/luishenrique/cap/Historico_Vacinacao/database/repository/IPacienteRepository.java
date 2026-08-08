package com.luishenrique.cap.Historico_Vacinacao.database.repository;
import com.luishenrique.cap.Historico_Vacinacao.database.models.PacienteEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<PacienteEntity, Long> {
    @EntityGraph(attributePaths = {"municipio", "municipio.estado"})
    List<PacienteEntity> findAll();

    List<PacienteEntity> findByCpf(String cpf);
}