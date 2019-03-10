package br.com.digicade.exame.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.digicade.exame.domain.Curso;
import br.com.digicade.exame.domain.Matricula;
import br.com.digicade.exame.service.CursoService;
import br.com.digicade.exame.service.MatriculaService;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.exception.OperacaoNaoPermitida;
import br.com.digicade.exame.service.mapper.CursoMapper;
import br.com.digicade.exame.service.repository.CursoRepository;
import br.com.digicade.exame.utils.OffsetBasedPageRequest;
import br.com.digicade.exame.web.dto.CursoDTO;

/**
 * Implementaçao de CursoService
 * para gerenciar Curso
 */
@Service
public class CursoServiceImpl implements CursoService {
	
    /**
     * Instância de
     * {@link CursoRepository}
     * que será utilizada
     * para realizar requisiçoes
     * na tabela de Curso
     */
	private final CursoRepository repository;
	
    /**
     * Instância de
     * {@link MatriculaService}
     * que será utilizada
     * para acessar métodos
     * relacionados a Matricula
     */
	private final MatriculaService matriculaService;
	
    /**
     * Instância do mapper da
     * entidade Curso
     */
	private final CursoMapper cursoMapper;
	
    /**
     * Construtor da classe
     * {@link CursoServiceImpl}
     *
     * @param AlunoRepository
     * @param CursoMapper
     * @param MatriculaService
     */
	public CursoServiceImpl(CursoRepository repository, CursoMapper cursoMapper, MatriculaService matriculaService) {
		this.repository = repository;
		this.cursoMapper = cursoMapper;
		this.matriculaService = matriculaService;
	}
	
	/**
	 * Método para buscar todos
	 * os cursos cadastrados.
	 * Se os parâmetros page e size
	 * forem passados ao método,
	 * a consulta será paginada.
	 * Caso contrário, não haverá
	 * paginação.
	 * 
	 * @param page
	 * @param size
	 * 
	 * @return Page<CursoDTO>
	 */
	@Override
	public Page<CursoDTO> buscarTodos(Integer page, Integer size) {
		Pageable pageable = null;
		if (page != null && size != null) {
			pageable = new OffsetBasedPageRequest(page, size);
		}
		return this.repository.buscarTodos(pageable);
	}
	
	/**
	 * Método para persistir ou
	 * atualizar um registro
	 * de Curso
	 * 
	 * @param curso
	 */
	@Override
	public void criar(CursoDTO curso) {
		repository.save(cursoMapper.toEntity(curso));
	}

	/**
	 * Método para buscar um registro
	 * de Curso através da sua pk ID.
	 * Caso o registro não seja encontrado,
	 * será lançada uma exceção informando
	 * o ocorrido.
	 * 
	 * @param id
	 * @return Curso
	 */
	@Override
	public Curso buscarPorId(Long id) throws EntidadeNaoEncontrada {
		Curso curso = this.repository.findById(id).orElse(null);
		if (curso == null) {
			throw new EntidadeNaoEncontrada("Curso não encontrado");
		}

		return curso;
	}
	
	/**
	 * Método para excluir um
	 * registro no banco de dados
	 * através da sua pk ID
	 * 
	 * @param id
	 */
	@Override
	public void remover(Long id) throws OperacaoNaoPermitida, EntidadeNaoEncontrada {
		Curso curso = this.buscarPorId(id);
		if (!checkExisteAlunoMatriculadoNoCurso(id)) {
			this.repository.delete(curso);
		} else {
			throw new OperacaoNaoPermitida("O registro não pode ser excluído");
		}
	}
	
	/**
	 * Método utilizado para verificar
	 * se existe algum aluno matriculado
	 * no curso.
	 * 
	 * @param cursoId
	 * @return boolean
	 */
	private boolean checkExisteAlunoMatriculadoNoCurso(Long cursoId) {
		List<Matricula> listaMatriculas = matriculaService.buscarMatriculasPorCurso(cursoId);
		return !listaMatriculas.isEmpty();
	}

}
