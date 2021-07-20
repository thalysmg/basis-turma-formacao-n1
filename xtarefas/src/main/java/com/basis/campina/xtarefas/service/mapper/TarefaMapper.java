package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Tarefa;
import com.basis.campina.xtarefas.domain.dto.TarefaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(target = "responsavel.id", source = "idResponsavel")
    Tarefa toEntity(TarefaDTO dto);

    @Mapping(target = "idResponsavel", source = "responsavel.id")
    TarefaDTO toDto(Tarefa tarefa);
}
