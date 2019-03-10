package br.com.digicade.exame.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.digicade.exame.domain.Aluno;
import br.com.digicade.exame.service.AlunoService;
import br.com.digicade.exame.service.exception.AlunoJaCadastradoException;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.mapper.AlunoMapper;
import br.com.digicade.exame.service.repository.AlunoRepository;
import br.com.digicade.exame.utils.OffsetBasedPageRequest;
import br.com.digicade.exame.web.dto.AlunoDTO;

/**
 * Implementaçao de AlunoService
 * para gerenciar Aluno
 */
@Service
public class AlunoServiceImpl implements AlunoService {
	
    /**
     * Instância de
     * {@link AlunoRepository}
     * que será utilizada
     * para realizar requisiçoes
     * na tabela de Aluno
     */
	private final AlunoRepository repository;
	
    /**
     * Instância do mapper da
     * entidade Aluno
     */
	private final AlunoMapper alunoMapper;
	
    /**
     * Construtor da classe
     * {@link AlunoServiceImpl}
     *
     * @param AlunoRepository
     */
	public AlunoServiceImpl(AlunoRepository repository, AlunoMapper alunoMapper) {
		this.repository = repository;
		this.alunoMapper = alunoMapper;
	}
	
	/**
	 * Método para buscar todos
	 * os alunos cadastrados.
	 * Se os parâmetros page e size
	 * forem passados ao método,
	 * a consulta será paginada.
	 * Caso contrário, não haverá
	 * paginação.
	 * 
	 * @param page
	 * @param size
	 * 
	 * @return Page<AlunoDTO>
	 */
	@Override
    public Page<AlunoDTO> buscarTodos(Integer page, Integer size) {
		Pageable pageable = null;
		if (page != null && size != null) {
			pageable = new OffsetBasedPageRequest(page, size);
		}
		return this.repository.buscarTodos(pageable);
	}
	
	/**
	 * Método para persistir ou
	 * atualizar um registro
	 * de Aluno
	 * 
	 * @param alunoDTO
	 * 
	 */
	@Override
    public void criar(AlunoDTO alunoDTO) throws AlunoJaCadastradoException {
		try {
			this.repository.save(alunoMapper.toEntity(alunoDTO));
		} catch (DataIntegrityViolationException e) {
			throw new AlunoJaCadastradoException("CPF já cadastrado");
		}
		
	}
	
	/**
	 * Método para buscar um registro
	 * de Aluno através da sua pk ID.
	 * Caso o registro não seja encontrado,
	 * será lançada uma exceção informando
	 * o ocorrido.
	 * 
	 * @param id
	 * @return Aluno
	 */
	@Override
    public Aluno buscarPorId(Long id) throws EntidadeNaoEncontrada {
		Aluno aluno = this.repository.findById(id).orElse(null);
		if (aluno == null) {
			throw new EntidadeNaoEncontrada("Aluno não encontrado");
		}

		return aluno;
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
		Aluno aluno = this.buscarPorId(id);
		this.repository.delete(aluno);
	}
}
