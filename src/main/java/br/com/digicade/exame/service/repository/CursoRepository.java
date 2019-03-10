package br.com.digicade.exame.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.digicade.exame.domain.Curso;
import br.com.digicade.exame.web.dto.CursoDTO;

public interface CursoRepository extends CrudRepository<Curso, Long> {

	/**
	 * Método para realizar uma 
	 * consulta no banco de dados
	 * e buscar todos os registros
	 * de Curso.
	 * O método possui a opçao de
	 * paginar a consulta, basta
	 * enviar o objeto pageable
	 * preenchido com os devidos
	 * valores.
	 * 
	 * @param pageable
	 * @return Page<AlunoDTO>
	 */
	@Query(value = "SELECT new br.com.digicade.exame.web.dto.CursoDTO (c.id,c.nome,c.dataInicio,c.dataFim) FROM Curso c order by c.id desc")
	Page<CursoDTO> buscarTodos(Pageable pageable);
	
}
