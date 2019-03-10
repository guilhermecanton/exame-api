package br.com.digicade.exame.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.digicade.exame.domain.Aluno;
import br.com.digicade.exame.web.dto.AlunoDTO;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
	
	/**
	 * Método para realizar uma 
	 * consulta no banco de dados
	 * e buscar todos os registros
	 * de Aluno.
	 * O método possui a opçao de
	 * paginar a consulta, basta
	 * enviar o objeto pageable
	 * preenchido com os devidos
	 * valores.
	 * 
	 * 
	 * @param pageable
	 * @return Page<AlunoDTO>
	 */
	@Query(value = "SELECT new br.com.digicade.exame.web.dto.AlunoDTO (a.id,a.nome,a.cpf,a.email,a.dataNascimento) FROM Aluno a order by a.id desc")
	Page<AlunoDTO> buscarTodos(Pageable pageable);
	
}
