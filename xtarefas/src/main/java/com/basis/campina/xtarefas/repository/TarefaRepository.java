package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.domain.elasticsearch.TarefaDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("select new com.basis.campina.xtarefas.domain.elasticsearch.TarefaDocument(" +
            "t.id, t.nome, t.dataInicio, t.dataConclusao, t.status, t.responsavel.id) from Tarefa t where t.id = :id")
    TarefaDocument getDocument(@Param("id") Long id);
}
