package com.luishenrique.cap.Historico_Vacinacao.database.repository;

import com.luishenrique.cap.Historico_Vacinacao.database.models.RegistroVacinacaoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRegistroRepository extends JpaRepository<RegistroVacinacaoEntity, Long> {
    @EntityGraph(attributePaths = {
            // carrega a vacina e o fabricante dela
            "vacina", "vacina.fabricante",
            // carrega o paciente, o município dele e o estado do município
            "paciente", "paciente.municipio","paciente.municipio.estado",
            // carrega o profissional, a unidade dele, o município da unidade e o estado
            "profissional", "profissional.unidade", "profissional.unidade.municipio", "profissional.unidade.municipio.estado",
            // carrega a unidade do registro, o município e o estado
            "unidade", "unidade.municipio", "unidade.municipio.estado",
    })
        public List<RegistroVacinacaoEntity> findAll();
}
