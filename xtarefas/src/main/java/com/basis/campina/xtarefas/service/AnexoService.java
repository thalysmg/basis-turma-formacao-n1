package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.domain.dto.AnexoDTO;
import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.service.feign.ArquivoClient;
import com.basis.campina.xtarefas.service.mapper.AnexoMapper;
import lombok.AllArgsConstructor;
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

    private final ArquivoClient client;

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
        return mapper.toDto(repository.saveAndFlush(mapper.toEntity(anexoDto)));
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
