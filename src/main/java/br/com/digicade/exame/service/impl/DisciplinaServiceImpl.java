package br.com.digicade.exame.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.digicade.exame.domain.Disciplina;
import br.com.digicade.exame.service.DisciplinaService;
import br.com.digicade.exame.service.MatriculaService;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.exception.OperacaoNaoPermitida;
import br.com.digicade.exame.service.mapper.DisciplinaMapper;
import br.com.digicade.exame.service.repository.DisciplinaRepository;
import br.com.digicade.exame.utils.OffsetBasedPageRequest;
import br.com.digicade.exame.web.dto.DisciplinaDTO;

/**
 * Implementaçao de DisciplinaService
 * para gerenciar Disciplina
 */
@Service
public class DisciplinaServiceImpl implements DisciplinaService {
	
    /**
     * Instância de
     * {@link DisciplinaRepository}
     * que será utilizada
     * para realizar requisiçoes
     * na tabela de Disciplina
     */
    private final DisciplinaRepository repository;
    
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
     * entidade Disciplina
     */
    private final DisciplinaMapper disciplinaMapper;
    
    /**
     * Construtor da classe
     * {@link DisciplinaServiceImpl}
     *
     * @param DisciplinaRepository
     * @param DisciplinaMapper
     * @param MatriculaService
     */
    public DisciplinaServiceImpl(DisciplinaRepository repository,
                                 DisciplinaMapper disciplinaMapper,
                                 MatriculaService matriculaService) {
        this.repository = repository;
        this.disciplinaMapper = disciplinaMapper;
        this.matriculaService = matriculaService;
    }
    
    /**
	 * Método para buscar todas
	 * as disciplinas cadastradas.
	 * O método recebe dois parâmetros,
	 * utilizados para realizar a paginação
	 * da consulta
	 * 
	 * @param page
	 * @param size
	 * 
	 * @return Page<DisciplinaDTO>
	 */
    @Override
    public Page<DisciplinaDTO> buscarTodos(Integer page, Integer size) {
    	Pageable pageable = new OffsetBasedPageRequest(page, size);
		return this.repository.buscarTodos(pageable);
    }
    
	/**
	 * Método para persistir ou
	 * atualizar um registro
	 * de Disciplina
	 * 
	 * @param disciplina
	 */
    @Override
    public void criar(DisciplinaDTO disciplina) {
        repository.save(disciplinaMapper.toEntity(disciplina));
    }
    
    /**
	 * Método para buscar um registro
	 * de Disciplina através da sua pk ID.
	 * Caso o registro não seja encontrado,
	 * será lançada uma exceção informando
	 * o ocorrido.
	 * 
	 * @param id
	 * @return Disciplina
	 */
    @Override
    public Disciplina buscarPorId(Long id) throws EntidadeNaoEncontrada {
        Disciplina disciplina = this.repository.findById(id).orElse(null);
        if (disciplina == null) {
            throw new EntidadeNaoEncontrada("Disciplina não encontrado");
        }

        return disciplina;
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
        Disciplina disciplina = this.buscarPorId(id);
        if (verificarSeDisciplinaPodeSerExcluida(id)) {
            this.repository.delete(disciplina);
        } else {
            throw new OperacaoNaoPermitida("O registro não pode ser excluído");
        }

    }

    /**
     * Método utilizado para validar
     * se uma disciplina pode ser excluída.
     * O registro só poderá ser excluído
     * se ele não estiver associado a algum
     * curso que possua pelo menos um aluno
     * matriculado
     * 
     * @param idDisciplina
     * @return
     */
    private boolean verificarSeDisciplinaPodeSerExcluida(Long idDisciplina) {
        return (matriculaService.buscarMatriculaPorCursoDeUmaDisciplina(idDisciplina) == 0) ? true : false;
    }

}
