package br.com.digicade.exame.service;

import org.springframework.data.domain.Page;

import br.com.digicade.exame.domain.Disciplina;
import br.com.digicade.exame.service.exception.AtributoNaoEncontrado;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.exception.OperacaoNaoPermitida;
import br.com.digicade.exame.service.impl.DisciplinaServiceImpl;
import br.com.digicade.exame.web.dto.DisciplinaDTO;

/**
 * Interface de serviço para 
 * gerenciar Disciplina
 * 
 * @see DisciplinaServiceImpl
 */
public interface DisciplinaService {

    public Page<DisciplinaDTO> buscarTodos(Integer page, Integer size);

    public void criar(DisciplinaDTO disciplina) throws AtributoNaoEncontrado;

    public Disciplina buscarPorId(Long id) throws EntidadeNaoEncontrada;

    public void remover(Long id) throws OperacaoNaoPermitida, EntidadeNaoEncontrada;

}
