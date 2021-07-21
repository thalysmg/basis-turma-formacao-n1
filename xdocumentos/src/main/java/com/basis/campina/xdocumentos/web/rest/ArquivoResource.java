package com.basis.campina.xdocumentos.web.rest;

import com.basis.campina.xdocumentos.domain.dto.ArquivoDTO;
import com.basis.campina.xdocumentos.service.ArquivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/arquivos")
public class ArquivoResource {

    private final ArquivoService arquivoService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ArquivoDTO> buscarArquivo(@PathVariable String uuid) {
        return ResponseEntity.ok(arquivoService.getDocument(uuid));
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody ArquivoDTO arquivoDTO) {
        return ResponseEntity.ok(arquivoService.salvar(arquivoDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> excluir(@PathVariable String uuid) {
        arquivoService.deletar(uuid);
        return ResponseEntity.ok().build();
    }
}
