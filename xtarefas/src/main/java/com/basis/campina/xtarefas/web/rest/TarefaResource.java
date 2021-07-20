package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.domain.dto.TarefaDTO;
import com.basis.campina.xtarefas.service.TarefaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/tarefas")
public class TarefaResource {

    private final TarefaService service;

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.buscarPorId(id));
    }

    @GetMapping("")
    public ResponseEntity<List<TarefaDTO>> listar() {
        return ResponseEntity.ok(this.service.listarTodos());
    }

    @PostMapping("")
    public ResponseEntity<TarefaDTO> salvar(@RequestBody TarefaDTO tarefaDto) {
        return ResponseEntity.ok(this.service.salvar(tarefaDto));
    }

    @PutMapping("")
    public ResponseEntity<TarefaDTO> editar(@RequestBody TarefaDTO tarefaDto) {
        return ResponseEntity.ok(this.service.editar(tarefaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirPorId(@PathVariable Long id) {
        this.service.excluirPorId(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
