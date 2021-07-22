package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.repository.ResponsavelRepository;
import com.basis.campina.xtarefas.service.event.ResponsavelEvent;
import com.basis.campina.xtarefas.service.mapper.ResponsavelMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository repository;
    private final ResponsavelMapper mapper;
    private final ApplicationEventPublisher appEventPublisher;

    public List<ResponsavelDTO> listarTodos() {
        return this.repository.findAll().stream()
                .map(responsavel -> mapper.toDto(responsavel)).collect(Collectors.toList());
    }

    public ResponsavelDTO buscarPorId(Long id) {
        return mapper.toDto(this.repository.findById(id).get());
    }

    public ResponsavelDTO salvar(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = mapper.toEntity(responsavelDTO);
        responsavel = this.repository.saveAndFlush(responsavel);
        appEventPublisher.publishEvent(new ResponsavelEvent(responsavel.getId()));
        return mapper.toDto(responsavel);
    }

    public ResponsavelDTO editar(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = mapper.toEntity(responsavelDTO);
        responsavel = this.repository.saveAndFlush(responsavel);
        return mapper.toDto(responsavel);
    }

    @Transactional
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}

