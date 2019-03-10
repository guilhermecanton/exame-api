package br.com.digicade.exame.service;

import org.springframework.data.domain.Page;

import br.com.digicade.exame.domain.Curso;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.exception.OperacaoNaoPermitida;
import br.com.digicade.exame.service.impl.CursoServiceImpl;
import br.com.digicade.exame.web.dto.CursoDTO;

/**
 * Interface de serviço para 
 * gerenciar Curso
 * 
 * @see CursoServiceImpl
 */
public interface CursoService {

	public Page<CursoDTO> buscarTodos(Integer page, Integer size);

	public void criar(CursoDTO curso);

	public Curso buscarPorId(Long id) throws EntidadeNaoEncontrada;

	public void remover(Long id) throws OperacaoNaoPermitida, EntidadeNaoEncontrada;

}
