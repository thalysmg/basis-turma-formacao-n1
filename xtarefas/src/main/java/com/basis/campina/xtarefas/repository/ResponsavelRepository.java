package com.basis.campina.xtarefas.repository;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.elasticsearch.ResponsavelDocument;
import com.basis.campina.xtarefas.repository.elastic.Reindexer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>, Reindexer {

    @Query("select new com.basis.campina.xtarefas.domain.elasticsearch.ResponsavelDocument( " +
            "r.id, r.nome, r.email, r.dataNascimento ) from Responsavel r where r.id = :id")
    ResponsavelDocument getDocument(@Param("id") Long id);

    @Query("select new com.basis.campina.xtarefas.domain.elasticsearch.ResponsavelDocument( " +
            "r.id, r.nome, r.email, r.dataNascimento ) from Responsavel r order by r.id")
    Page<ResponsavelDocument> reindexPage(Pageable page);

    @Override
    default String getEntity() {
        return  "responsavel";
    }
}
