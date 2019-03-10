package br.com.digicade.exame.web.dto;

public class DisciplinaDTO {

	private Long id;

	private String nome;

	private Long idCurso;

	private String nomeCurso;

	public DisciplinaDTO(Long id, String nome, Long idCurso, String nomeCurso) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeCurso = nomeCurso;
		this.idCurso = idCurso;
	}

	public DisciplinaDTO() {
		super();
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

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

}
