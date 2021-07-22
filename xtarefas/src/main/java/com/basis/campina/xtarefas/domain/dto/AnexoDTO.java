package com.basis.campina.xtarefas.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnexoDTO {

    private Long id;

    private String file;

    private String fileName;

    private Long idTarefa;

    private String uuid;
}
