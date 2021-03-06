package com.ApiCrdb.crdb.DTO;

import com.ApiCrdb.crdb.Entidades.Usuario;

public class UsuarioDTO {
	
	private String email;
	private String nome;
	
	public UsuarioDTO(Usuario usuario) {
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
