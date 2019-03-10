package br.com.digicade.exame.web.dto;

public class DisciplinaCadastroDTO {
	private Long id;

	private String nome;

	private CursoDTO curso;

	public DisciplinaCadastroDTO() {
		super();
	}

	public DisciplinaCadastroDTO(Long id, String nome, CursoDTO curso) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CursoDTO getCurso() {
		return curso;
	}

	public void setCurso(CursoDTO curso) {
		this.curso = curso;
	}
}
