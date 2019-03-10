package br.com.digicade.exame.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.digicade.exame.domain.Matricula;
import br.com.digicade.exame.web.dto.MatriculaDTO;

/**
 * Classe utilizada no acesso a
 * tabela tb_matricula no banco
 * de dados da entidade Matricula
 * 
 * Extensão de CrudRepository
 */
public interface MatriculaRepository extends CrudRepository<Matricula, Long> {
	
	/**
	 * Método para realizar uma 
	 * consulta no banco de dados
	 * e buscar todos os registros
	 * de Matricula pelo id do curso
	 * 
	 * @param pageable
	 * @return Page<AlunoDTO>
	 */
    @Query("select m from Matricula m where m.curso.id = :idCurso")
    List<Matricula> buscarMatriculasPorCurso(@Param("idCurso") Long idCurso);
    
    /**
     * Método que realizar uma consulta
     * para buscar a quantidade
     * de registros de matricula que
     * tenham a fk do Curso de uma
     * determinada disciplina.
     * 
     * @param idDisciplina
     * @return Long
     */
    @Query("select count(m) from Matricula m inner join Curso c on (m.curso.id = c.id) inner join Disciplina d on (d.curso.id = c.id) where d.id =:idDisciplina")
    Long buscarMatriculaPorCursoDeUmaDisciplina(@Param("idDisciplina") Long idDisciplina);
    
	/**
	 * Método para realizar uma 
	 * consulta no banco de dados
	 * e buscar todos os registros
	 * de Matricula.
	 * O método possui a opçao de
	 * paginar a consulta, basta
	 * enviar o objeto pageable
	 * preenchido com os devidos
	 * valores.
	 * 
	 * @param pageable
	 * @return Page<MatriculaDTO>
	 */
	@Query(value = "SELECT new br.com.digicade.exame.web.dto.MatriculaDTO (m.id,m.curso.nome,m.curso.id,m.aluno.nome,m.aluno.id,m.dataMatricula,m.turno) FROM Matricula m order by m.id desc")
	Page<MatriculaDTO> buscarTodos(Pageable pageable);
}
