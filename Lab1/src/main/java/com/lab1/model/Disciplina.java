package com.lab1.model;

public class Disciplina {
	
	private int id;
	private String nome;
	private double nota;
	
	public Disciplina(String nome, double nota) {
		this.setNome(nome);
		this.setNota(nota);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
