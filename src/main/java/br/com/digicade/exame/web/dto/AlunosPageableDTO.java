package br.com.digicade.exame.web.dto;

import java.util.List;

public class AlunosPageableDTO {

	private Long totalRegistros;
	private List<AlunoDTO> alunos;

	public AlunosPageableDTO() {
		super();
	}

	public AlunosPageableDTO(Long totalRegistros, List<AlunoDTO> alunos) {
		super();
		this.totalRegistros = totalRegistros;
		this.alunos = alunos;
	}

	public Long getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public List<AlunoDTO> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoDTO> alunos) {
		this.alunos = alunos;
	}

}
