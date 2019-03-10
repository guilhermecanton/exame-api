package br.com.digicade.exame.service;

import org.springframework.data.domain.Page;

import br.com.digicade.exame.domain.Aluno;
import br.com.digicade.exame.service.exception.AlunoJaCadastradoException;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.impl.AlunoServiceImpl;
import br.com.digicade.exame.web.dto.AlunoDTO;

/**
 * Interface de serviço para 
 * gerenciar Aluno
 * 
 * @see AlunoServiceImpl
 */
public interface AlunoService {
	
	
	public Page<AlunoDTO> buscarTodos(Integer page, Integer size);
	
	public void criar(AlunoDTO aluno) throws AlunoJaCadastradoException;

	public Aluno buscarPorId(Long id) throws EntidadeNaoEncontrada;

	public void remover(Long id) throws EntidadeNaoEncontrada;

	
}
