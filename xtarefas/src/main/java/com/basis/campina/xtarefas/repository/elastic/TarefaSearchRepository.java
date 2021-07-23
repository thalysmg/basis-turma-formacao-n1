package com.basis.campina.xtarefas.repository.elastic;

import com.basis.campina.xtarefas.domain.elasticsearch.TarefaDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TarefaSearchRepository extends ElasticEntity<TarefaDocument, Long> {

    @Override
    default Class<TarefaDocument> getEntityClass() {
        return TarefaDocument.class;
    }
}
