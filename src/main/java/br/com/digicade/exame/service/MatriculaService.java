package br.com.digicade.exame.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.digicade.exame.domain.Matricula;
import br.com.digicade.exame.service.exception.AtributoNaoEncontrado;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.impl.MatriculaServiceImpl;
import br.com.digicade.exame.web.dto.MatriculaDTO;

/**
 * Interface de serviço para 
 * gerenciar Matricula
 * 
 * @see MatriculaServiceImpl
 */
public interface MatriculaService {

    public Page<MatriculaDTO> buscarTodos(Integer page, Integer size);

    public void criar(MatriculaDTO matricula) throws AtributoNaoEncontrado;

    public Matricula buscarPorId(Long id) throws EntidadeNaoEncontrada;

	public void remover(Long id) throws EntidadeNaoEncontrada;

    public List<Matricula> buscarMatriculasPorCurso(Long cursoId);

    public Long buscarMatriculaPorCursoDeUmaDisciplina(Long disciplinaId);
}
