package com.basis.campina.xtarefas.service.mapper;

import com.basis.campina.xtarefas.domain.Responsavel;
import com.basis.campina.xtarefas.domain.dto.ResponsavelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper {

    Responsavel toEntity(ResponsavelDTO dto);

    ResponsavelDTO toDto(Responsavel responsavel);
}
