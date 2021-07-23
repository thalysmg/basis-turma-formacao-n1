package com.basis.campina.xtarefas.service.filter;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelFilter extends DefaultFilter implements BaseFilter, Serializable {

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "nome", "email", "status");

        addShouldTermQuery(queryBuilder,"dataNascimento", query);

        return queryBuilder;
    }
}
