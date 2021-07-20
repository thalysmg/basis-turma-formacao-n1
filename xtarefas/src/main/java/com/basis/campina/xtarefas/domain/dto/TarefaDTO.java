package com.basis.campina.xtarefas.domain.dto;

import com.basis.campina.xtarefas.enumeration.StatusTarefa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TarefaDTO {

    private Long id;

    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataConclusao;

    private StatusTarefa status;

    private int idResponsavel;
}
