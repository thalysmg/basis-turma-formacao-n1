package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.service.elastic.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/elasticsearch/reindex")
@RequiredArgsConstructor
public class ElasticSearchResource {

    private final ElasticSearchService elasticSearchService;

    @GetMapping
    public ResponseEntity<String> reindex() {
        this.elasticSearchService.reindex();
        return ResponseEntity.ok("Reindexando todo o elasticsearch.");
    }

    @GetMapping("/{entity}")
    public ResponseEntity<String> reindex(@PathVariable String entity) {
        this.elasticSearchService.reindexEntity(entity);
        return ResponseEntity.ok("Reindexando o elasticsearch de: " + entity +".");
    }
}
