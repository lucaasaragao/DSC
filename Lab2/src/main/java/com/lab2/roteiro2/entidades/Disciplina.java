package com.lab2.roteiro2.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Disciplina {
	
	@Id@GeneratedValue
	
	private long id;
	private String nome;
	private Double nota;
	private String comentarios;
	private int likes;
	
	public Disciplina() {
		super();
		this.nome = "Materia";
		this.nota = 1.0;
		this.comentarios = "Está indo bem";
		this.likes = 0;
	}
	
	public Disciplina(String nome) {
		super();
		this.nome = nome;
		this.nota = 1.0;
		this.comentarios = "";
		this.likes = 0;
		
	}
	
	public Disciplina( String nome, Double nota, String comentario, int like) {
		super();
		this.nome = nome;
		this.nota = nota;
		this.comentarios = comentario;
		this.likes = like;
	} 

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
}
