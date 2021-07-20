package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.domain.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.service.ResponsavelService;
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
@RequestMapping("api/responsaveis")
public class ResponsavelResource {

    private final ResponsavelService service;

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.buscarPorId(id));
    }

    @GetMapping("")
    public ResponseEntity<List<ResponsavelDTO>> listar() {
        return ResponseEntity.ok(this.service.listarTodos());
    }

    @PostMapping("")
    public ResponseEntity<ResponsavelDTO> salvar(@RequestBody ResponsavelDTO responsavelDto) {
        return ResponseEntity.ok(this.service.salvar(responsavelDto));
    }

    @PutMapping("")
    public ResponseEntity<ResponsavelDTO> editar(@RequestBody ResponsavelDTO responsavelDto) {
        return ResponseEntity.ok(this.service.editar(responsavelDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluirPorId(@PathVariable Long id) {
        this.service.excluirPorId(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
