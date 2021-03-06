package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.domain.dto.AnexoDTO;
import com.basis.campina.xtarefas.domain.elasticsearch.AnexoDocument;
import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.repository.elastic.AnexoSearchRepository;
import com.basis.campina.xtarefas.service.event.AnexoEvent;
import com.basis.campina.xtarefas.service.feign.ArquivoClient;
import com.basis.campina.xtarefas.service.filter.AnexoFilter;
import com.basis.campina.xtarefas.service.mapper.AnexoMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AnexoService {

    private final AnexoRepository repository;
    private final AnexoMapper mapper;
    private final ApplicationEventPublisher appEventPublisher;
    private final AnexoSearchRepository searchRepository;
    private final ArquivoClient client;

    public Page<AnexoDocument> pesquisarFiltro(AnexoFilter filter, Pageable page) {
        return searchRepository.search(filter.getFilter(), page);
    }

    public List<AnexoDTO> listarTodos() {
        return this.repository.findAll().stream()
                .map(anexo -> mapper.toDto(anexo)).collect(Collectors.toList());
    }

    public AnexoDTO buscarPorId(Long id) throws Exception {
        AnexoDTO anexo = mapper.toDto(this.repository.findById(id)
                .orElseThrow(() -> new Exception("Arquivo não existe")));
        client.buscarArquivo(anexo.getFile());
        return anexo;
    }

    public AnexoDTO salvar(AnexoDTO anexoDto) {
        anexoDto.setUuid(UUID.randomUUID().toString());
        anexoDto.setFile(client.salvar(anexoDto));
        Anexo anexo = repository.saveAndFlush(mapper.toEntity(anexoDto));
        appEventPublisher.publishEvent(new AnexoEvent(anexo.getId()));
        return mapper.toDto(anexo);
    }

    public AnexoDTO editar(AnexoDTO anexoDto) {
        Anexo anexo = mapper.toEntity(anexoDto);
        anexo = this.repository.saveAndFlush(anexo);
        return mapper.toDto(anexo);
    }

    @Transactional
    public void excluirPorId(Long id) throws Exception {
        AnexoDTO anexo = this.buscarPorId(id);
        client.deletar(anexo.getFile());
        repository.deleteById(id);
    }
}
