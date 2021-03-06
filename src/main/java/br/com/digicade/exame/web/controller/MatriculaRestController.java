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

import br.com.digicade.exame.service.MatriculaService;
import br.com.digicade.exame.service.exception.EntidadeNaoEncontrada;
import br.com.digicade.exame.utils.ConstantsUtils;
import br.com.digicade.exame.web.dto.MatriculaDTO;

/**
 * Classe destinada a gerenciar as
 * requisições HTTP relacionadas a
 * entidade Matricula
 * 
 */
@CrossOrigin(origins = ConstantsUtils.REQUEST_ORIGIN)
@RestController
@RequestMapping("/api/v1/matriculas")
public class MatriculaRestController {
	
	/**
     * Serviço injetado para
     * execução de metodos de
     * MatriculaService
     */
    private final MatriculaService matriculaService;
    
    /**
     * Instância da classe Logger
     * utilizada para gerar logs
     * em tempo de execução
     */
    Logger logger = LoggerFactory.getLogger(MatriculaRestController.class);
    
    /**
     * Construtor da classe
     * @param MatriculaService
     */
    public MatriculaRestController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }
    
	/**
	 * Endpoint utilizado para 
	 * buscar todos os registros
	 * de Matricula cadastrados
	 * no banco de dados
	 */
	@GetMapping("/")
	public Page<MatriculaDTO> buscar(@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "page", required = false) Integer page) {
		return matriculaService.buscarTodos(page, size);
	}
	
	/**
	 * Endpoint utilizado para 
	 * persistir um novo registro
	 * de Matricula ou atualizar um
	 * registro ja existente
	 * no banco de dados
	 * 
	 * @param DisciplinaDTO
	 */
    @PostMapping("/")
    public ResponseEntity<?> criar(@RequestBody MatriculaDTO dto) {
        try {
            matriculaService.criar(dto);
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
            matriculaService.remover(id);
        } catch (EntidadeNaoEncontrada e) {
            logger.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno");
        }
    }

}
