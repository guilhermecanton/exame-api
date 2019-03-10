package br.com.digicade.exame.web.dto;

import java.util.Date;

import br.com.digicade.exame.enumeration.Turno;

public class MatriculaDTO {

	private Long id;

	private String nomeCurso;

	private Long idCurso;

	private String nomeAluno;

	private Long idAluno;

	private Date dataMatricula;

	private Turno turno;

	public MatriculaDTO() {
		super();
	}

	public MatriculaDTO(Long id, String nomeCurso, Long idCurso, String nomeAluno, Long idAluno,
			Date dataMatricula, Turno turno) {
		super();
		this.id = id;
		this.nomeCurso = nomeCurso;
		this.idCurso = idCurso;
		this.nomeAluno = nomeAluno;
		this.idAluno = idAluno;
		this.dataMatricula = dataMatricula;
		this.turno = turno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}
