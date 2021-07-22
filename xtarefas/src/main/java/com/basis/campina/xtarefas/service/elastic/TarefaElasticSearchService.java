package com.basis.campina.xtarefas.service.elastic;

import com.basis.campina.xtarefas.domain.elasticsearch.TarefaDocument;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.repository.elastic.TarefaSearchRepository;
import com.basis.campina.xtarefas.service.event.TarefaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TarefaElasticSearchService {

    private final TarefaRepository repository;
    private final TarefaSearchRepository searchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void salvar(TarefaEvent event) {
        log.info("Iniciando indexação de responsável a partir de: {}", event.getId());
        TarefaDocument tarefaDocument = repository.getDocument(event.getId());
        searchRepository.save(tarefaDocument);
    }
}
