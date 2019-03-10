package br.com.digicade.exame.service.mapper;

import org.mapstruct.Mapper;

import br.com.digicade.exame.domain.Aluno;
import br.com.digicade.exame.web.dto.AlunoDTO;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

	Aluno toEntity(AlunoDTO dto);

	AlunoDTO toDto(Aluno entity);

}
