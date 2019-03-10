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

import br.com.digicade.exame.service.AlunoService;
import br.com.digicade.exame.service.exception.AlunoJaCadastradoException;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.utils.ConstantsUtils;
import br.com.digicade.exame.web.dto.AlunoDTO;

/**
 * Classe destinada a gerenciar as
 * requisições HTTP relacionadas a
 * entidade Aluno
 * 
 */
@CrossOrigin(origins = ConstantsUtils.REQUEST_ORIGIN)
@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoRestController {
	
    /**
     * Serviço injetado para
     * execução de metodos de
     * AlunoService
     */
	private final AlunoService alunoService;
	
    /**
     * Instância da classe Logger
     * utilizada para gerar logs
     * em tempo de execução
     */
	Logger logger = LoggerFactory.getLogger(CursoRestController.class);
	
    /**
     * Construtor da classe
     * @param AlunoService
     */
	public AlunoRestController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	/**
	 * Endpoint utilizado para 
	 * buscar todos os registros
	 * de Aluno cadastrados
	 * no banco de dados
	 */
	@GetMapping("/")
	public Page<AlunoDTO> buscar(@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page) {
		return alunoService.buscarTodos(page, size);
	}
	
	/**
	 * Endpoint utilizado para 
	 * persistir um novo registro
	 * de Aluno ou atualizar um
	 * registro ja existente
	 * no banco de dados
	 * 
	 * @param AlunoDTO
	 */
	@PostMapping("/")
	public ResponseEntity<?> criar(@RequestBody AlunoDTO dto) {
		try {
			alunoService.criar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (AlunoJaCadastradoException e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
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
			alunoService.remover(id);
		} catch (EntidadeNaoEncontrada e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno");
		}
	}

}
