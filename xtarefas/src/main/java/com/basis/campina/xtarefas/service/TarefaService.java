package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.domain.dto.TarefaDTO;
import com.basis.campina.xtarefas.domain.elasticsearch.TarefaDocument;
import com.basis.campina.xtarefas.repository.TarefaRepository;
import com.basis.campina.xtarefas.repository.elastic.TarefaSearchRepository;
import com.basis.campina.xtarefas.service.event.TarefaEvent;
import com.basis.campina.xtarefas.service.filter.TarefaFilter;
import com.basis.campina.xtarefas.service.mapper.TarefaMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TarefaService {

    private TarefaRepository repository;
    private TarefaMapper mapper;
    private ApplicationEventPublisher appEventPublisher;
    private TarefaSearchRepository searchRepository;

    public List<TarefaDTO> listarTodos() {
        return this.repository.findAll().stream()
                .map(tarefa -> mapper.toDto(tarefa)).collect(Collectors.toList());
    }

    public TarefaDTO buscarPorId(Long id) {
        Tarefa tarefa = this.repository.findById(id).get();
        return mapper.toDto(tarefa);
    }

    public TarefaDTO salvar(TarefaDTO tarefaDto) {
        Tarefa tarefa = mapper.toEntity(tarefaDto);
        tarefa = this.repository.saveAndFlush(tarefa);
        appEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        return mapper.toDto(tarefa);
    }

    public TarefaDTO editar(TarefaDTO tarefaDto) {
        Tarefa tarefa = mapper.toEntity(tarefaDto);
        tarefa = this.repository.saveAndFlush(tarefa);
        return mapper.toDto(tarefa);
    }

    @Transactional
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public Page<TarefaDocument> pesquisarFiltro(TarefaFilter filter, Pageable page) {
        return this.searchRepository.search(filter.getFilter(), page);
    }
}
