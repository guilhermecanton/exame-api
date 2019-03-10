package br.com.digicade.exame.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.digicade.exame.domain.Matricula;
import br.com.digicade.exame.web.dto.MatriculaDTO;

@Mapper(componentModel = "spring", uses = {CursoMapper.class, AlunoMapper.class})
public interface MatriculaMapper {
	
	@Mapping(source = "idCurso", target = "curso.id")
	@Mapping(source = "nomeCurso", target = "curso.nome")
	@Mapping(source = "idAluno", target = "aluno.id")
	@Mapping(source = "nomeAluno", target = "aluno.nome")
    Matricula toEntity(MatriculaDTO dto);
	
	@Mapping(source = "curso.id", target = "idCurso")
	@Mapping(source = "curso.nome", target = "nomeCurso")
	@Mapping(source = "aluno.id", target = "idAluno")
	@Mapping(source = "aluno.nome", target = "nomeAluno")
    MatriculaDTO toDto(Matricula entity);
	
}
