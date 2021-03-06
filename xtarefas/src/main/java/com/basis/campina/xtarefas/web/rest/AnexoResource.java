package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.domain.dto.AnexoDTO;
import com.basis.campina.xtarefas.domain.elasticsearch.AnexoDocument;
import com.basis.campina.xtarefas.service.AnexoService;
import com.basis.campina.xtarefas.service.filter.AnexoFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("api/anexos")
public class AnexoResource {
    
    private final AnexoService service;

    @GetMapping("/{id}")
    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.service.buscarPorId(id));
    }

    @GetMapping("")
    public ResponseEntity<List<AnexoDTO>> listar() {
        return ResponseEntity.ok(this.service.listarTodos());
    }

    @PostMapping("")
    public ResponseEntity<AnexoDTO> salvar(@RequestBody AnexoDTO anexoDto) {
        return ResponseEntity.ok(this.service.salvar(anexoDto));
    }

    @PutMapping("")
    public ResponseEntity<AnexoDTO> editar(@RequestBody AnexoDTO anexoDto) {
        return ResponseEntity.ok(this.service.editar(anexoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirPorId(@PathVariable Long id) throws Exception {
        this.service.excluirPorId(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("/pesquisar")
    public ResponseEntity<Page<AnexoDocument>> pesquisar(@RequestBody AnexoFilter filter, Pageable page) {
        return ResponseEntity.ok(service.pesquisarFiltro(filter, page));
    }
}
