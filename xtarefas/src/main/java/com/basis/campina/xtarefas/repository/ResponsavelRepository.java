package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.elasticsearch.ResponsavelDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    @Query("select new com.basis.campina.xtarefas.domain.elasticsearch.ResponsavelDocument(" +
            "r.id, r.nome, r.email, r.dataNascimento ) from Responsavel r where r.id = :id")
    ResponsavelDocument getDocument(@Param("id") Long id);
}
