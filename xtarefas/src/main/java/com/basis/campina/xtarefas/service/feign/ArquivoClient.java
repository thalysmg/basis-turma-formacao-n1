package com.basis.campina.xtarefas.service.feign;

import com.basis.campina.xtarefas.domain.dto.AnexoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "xdocumentos", url = "${application.feign.url-documentos}")
public interface ArquivoClient {

    @GetMapping("api/arquivos/{uuid}")
    String buscarArquivo(@PathVariable String uuid);

    @PostMapping("api/arquivos")
    String salvar(AnexoDTO anexoDTO);

    @DeleteMapping("api/arquivos/{uuid}")
    void deletar(@PathVariable String uuid);

}
