package br.com.digicade.exame.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.digicade.exame.service.CursoService;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.service.exception.OperacaoNaoPermitida;
import br.com.digicade.exame.utils.ConstantsUtils;
import br.com.digicade.exame.web.dto.CursoDTO;

/**
 * Classe destinada a gerenciar as
 * requisições HTTP relacionadas a
 * entidade Curso
 * 
 */
@CrossOrigin(origins = ConstantsUtils.REQUEST_ORIGIN)
@RestController
@RequestMapping("/api/v1/cursos")
public class CursoRestController {
	
	/**
     * Serviço injetado para
     * execução de metodos de
     * CursoService
     */
	private final CursoService cursoService;
	
    /**
     * Instância da classe Logger
     * utilizada para gerar logs
     * em tempo de execução
     */
	Logger logger = LoggerFactory.getLogger(CursoRestController.class);
	
    /**
     * Construtor da classe
     * @param CursoService
     */
	public CursoRestController(CursoService cursoService) {
		this.cursoService = cursoService;
	}
	
	/**
	 * Endpoint utilizado para 
	 * buscar todos os registros
	 * de Curso cadastrados
	 * no banco de dados
	 */
	@GetMapping("/")
	public Page<CursoDTO> buscar(@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page) {
		return cursoService.buscarTodos(page, size);
	}
	
	@GetMapping("/teste")
	public String buscar() {
		return "I love Heroku";
	}
	
	/**
	 * Endpoint utilizado para 
	 * persistir um novo registro
	 * de Curso ou atualizar um
	 * registro ja existente
	 * no banco de dados
	 * 
	 * @param CursoDTO
	 */
	@PostMapping("/")
	public ResponseEntity<?> criar(@RequestBody CursoDTO dto) {
		try {
			cursoService.criar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno");
		}
	}

	/**
	 * Endpoint utilizado para
	 * excluir um registro
	 * através do seu ID
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(name = "id") Long id) {
		try {
			cursoService.remover(id);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (OperacaoNaoPermitida e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não é possível excluír o registro. Existe aluno matriculado neste curso.");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno");
		}
	}

}
