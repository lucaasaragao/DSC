package com.ApiCrdb.crdb.DTO;

import com.ApiCrdb.crdb.Entidades.Disciplina;

public class DisciplinaPorIdNome {
	
	private int id;
	private String nome;
	public DisciplinaPorIdNome(Disciplina disciplina) {
		super();
		this.id = disciplina.getId();
		this.nome = disciplina.getNome();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
