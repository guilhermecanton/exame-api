package br.com.digicade.exame.web.dto;

import java.util.Date;

public class CursoDTO {

	private Long id;

	private String nome;

	private Date dataInicio;

	private Date dataFim;
	

	public CursoDTO() {
		super();
	}

	public CursoDTO(Long id, String nome, Date dataInicio, Date dataFim) {
		this.id = id;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
