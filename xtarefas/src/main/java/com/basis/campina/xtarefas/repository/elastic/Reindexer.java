package com.basis.campina.xtarefas.repository.elastic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Reindexer {

    default String getEntity() {
        throw new IllegalAccessError("Método não implementado.");
    }

    <T>Page<T> reindexPage(Pageable page);
}
