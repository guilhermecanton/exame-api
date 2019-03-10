package br.com.digicade.exame.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.digicade.exame.domain.Disciplina;
import br.com.digicade.exame.web.dto.DisciplinaDTO;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
	
	/**
	 * Método para realizar uma 
	 * consulta no banco de dados
	 * e buscar todos os registros
	 * de Disciplina.
	 * O método possui a opçao de
	 * paginar a consulta, basta
	 * enviar o objeto pageable
	 * preenchido com os devidos
	 * valores.
	 * 
	 * @param pageable
	 * @return Page<AlunoDTO>
	 */
	@Query(value = "SELECT new br.com.digicade.exame.web.dto.DisciplinaDTO (d.id,d.nome,d.curso.id,d.curso.nome) FROM Disciplina d order by d.id desc")
	Page<DisciplinaDTO> buscarTodos(Pageable pageable);
	
}
