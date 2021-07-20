package com.basis.campina.xtarefas.service;

import com.basis.campina.xtarefas.domain.Anexo;
import com.basis.campina.xtarefas.domain.dto.AnexoDTO;
import com.basis.campina.xtarefas.repository.AnexoRepository;
import com.basis.campina.xtarefas.service.mapper.AnexoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AnexoService {

    private final AnexoRepository repository;

    private final AnexoMapper mapper;

    public List<AnexoDTO> listarTodos() {
        return this.repository.findAll().stream()
                .map(anexo -> mapper.toDto(anexo)).collect(Collectors.toList());
    }

    public AnexoDTO buscarPorId(Long id) {
        Anexo anexo = this.repository.findById(id).get();
        return mapper.toDto(anexo);
    }

    public AnexoDTO salvar(AnexoDTO anexoDto) {
        Anexo anexo = mapper.toEntity(anexoDto);
        anexo = this.repository.saveAndFlush(anexo);
        return mapper.toDto(anexo);
    }

    public AnexoDTO editar(AnexoDTO anexoDto) {
        Anexo anexo = mapper.toEntity(anexoDto);
        anexo = this.repository.saveAndFlush(anexo);
        return mapper.toDto(anexo);
    }

    @Transactional
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}
