package br.com.digicade.exame.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.digicade.exame.domain.Disciplina;
import br.com.digicade.exame.web.dto.DisciplinaDTO;

@Mapper(componentModel = "spring", uses = { CursoMapper.class })
public interface DisciplinaMapper {

	@Mapping(source = "idCurso", target = "curso.id")
	@Mapping(source = "nomeCurso", target = "curso.nome")
	Disciplina toEntity(DisciplinaDTO dto);

	@Mapping(source = "curso.id", target = "idCurso")
	@Mapping(source = "curso.nome", target = "nomeCurso")
	DisciplinaDTO toDto(Disciplina entity);

}
