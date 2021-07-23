package com.basis.campina.xtarefas.service.filter;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TarefaFilter extends DefaultFilter implements BaseFilter, Serializable {

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "nome", "status", "idResponsavel");

        addShouldTermQuery(queryBuilder, "dataConclusao", query);
        addShouldTermQuery(queryBuilder, "dataInicio", query);
        return queryBuilder;
    }
}
