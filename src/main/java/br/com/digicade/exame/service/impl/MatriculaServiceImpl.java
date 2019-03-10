package br.com.digicade.exame.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.digicade.exame.domain.Matricula;
import br.com.digicade.exame.service.MatriculaService;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.mapper.MatriculaMapper;
import br.com.digicade.exame.service.repository.MatriculaRepository;
import br.com.digicade.exame.utils.OffsetBasedPageRequest;
import br.com.digicade.exame.web.dto.MatriculaDTO;

/**
 * Implementaçao de MatriculaService
 * para gerenciar Matricula
 */
@Service
public class MatriculaServiceImpl implements MatriculaService {
	
    /**
     * Instância de
     * {@link MatriculaRepository}
     * que será utilizada
     * para realizar requisiçoes
     * na tabela de Matricula
     */
	private final MatriculaRepository repository;
	
    /**
     * Instância do mapper da
     * entidade Matricula
     */
	private final MatriculaMapper matriculaMapper;
	
    /**
     * Construtor da classe
     * {@link MatriculaServiceImpl}
     *
     * @param MatriculaRepository
     * @param MatriculaMapper
     */
	public MatriculaServiceImpl(MatriculaRepository repository, MatriculaMapper matriculaMapper) {
		this.repository = repository;
		this.matriculaMapper = matriculaMapper;
	}
	
    /**
	 * Método para buscar todas
	 * as matriculas cadastradas.
	 * O método recebe dois parâmetros,
	 * utilizados para realizar a paginação
	 * da consulta
	 * 
	 * @param page
	 * @param size
	 * 
	 * @return Page<MatriculaDTO>
	 */
	@Override
	public Page<MatriculaDTO> buscarTodos(Integer page, Integer size) {
		Pageable pageable = new OffsetBasedPageRequest(page, size);
		return repository.buscarTodos(pageable);
	}
	
	
    /**
	 * Método para buscar um registro
	 * de Matricula através da sua pk ID.
	 * Caso o registro não seja encontrado,
	 * será lançada uma exceção informando
	 * o ocorrido.
	 * 
	 * @param id
	 * @return Matricula
	 */
	@Override
	public Matricula buscarPorId(Long id) throws EntidadeNaoEncontrada {
		Matricula matricula = this.repository.findById(id).orElse(null);
		if (matricula == null) {
			throw new EntidadeNaoEncontrada("Matricula não encontrado");
		}

		return matricula;
	}
	
    /**
	 * Método para excluir um
	 * registro no banco de dados
	 * através da sua pk ID
	 * 
	 * @param id
	 */
	@Override
	public void remover(Long id) throws EntidadeNaoEncontrada {
		Matricula matricula = this.buscarPorId(id);
		this.repository.delete(matricula);
	}
	
	/**
	 * Método para persistir ou
	 * atualizar um registro
	 * de Matricula
	 * 
	 * @param matricula
	 */
	@Override
	public void criar(MatriculaDTO matricula) {
		verificarSeMatriculaJaPossuiData(matricula);
		repository.save(matriculaMapper.toEntity(matricula));
	}
	
	/**
	 * Método para buscar todos
	 * os registros de Matricula
	 * pelo ID do curso
	 * 
	 * @param cursoId
	 * @return List<Matricula>
	 */
	@Override
	public List<Matricula> buscarMatriculasPorCurso(Long cursoId) {
		return repository.buscarMatriculasPorCurso(cursoId);
	}
	
	/**
	 * Método para verificar
	 * se existe alguma matricula
	 * de um curso de uma determinada
	 * disciplina
	 * 
	 * @param disciplinaId
	 * @return Long
	 */
	@Override
	public Long buscarMatriculaPorCursoDeUmaDisciplina(Long disciplinaId) {
		return repository.buscarMatriculaPorCursoDeUmaDisciplina(disciplinaId);
	}
	
	/**
	 * Método para verificar se
	 * o objeto ja possui
	 * valor atribuido ao
	 * seu atributo dataMatricula
	 * 
	 * @param matricula
	 */
	private void verificarSeMatriculaJaPossuiData(MatriculaDTO matricula) {
		if (matricula.getDataMatricula() == null) {
			matricula.setDataMatricula(new Date());
		}
	}

}
