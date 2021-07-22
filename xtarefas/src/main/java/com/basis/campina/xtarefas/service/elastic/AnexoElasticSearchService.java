package com.basis.campina.xtarefas.service.elastic;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.domain.elasticsearch.AnexoDocument;
import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.repository.elastic.AnexoSearchRepository;
import com.basis.campina.xtarefas.service.event.AnexoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnexoElasticSearchService {

    private final AnexoRepository repository;
    private final AnexoSearchRepository searchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void salvar(AnexoEvent event) {
        log.info("Iniciando indexação de responsável a partir de: {}", event.getId());
        AnexoDocument anexoDocument = repository.getDocument(event.getId());
        searchRepository.save(anexoDocument);
    }
}
