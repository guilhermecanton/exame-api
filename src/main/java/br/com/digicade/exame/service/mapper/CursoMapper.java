package br.com.digicade.exame.service.mapper;

import org.mapstruct.Mapper;

import br.com.digicade.exame.domain.Curso;
import br.com.digicade.exame.service.impl.CursoServiceImpl;
import br.com.digicade.exame.web.dto.CursoDTO;

@Mapper(componentModel = "spring")
public interface CursoMapper {

	Curso toEntity(CursoDTO dto);

	CursoDTO toDto(Curso entity);

}
