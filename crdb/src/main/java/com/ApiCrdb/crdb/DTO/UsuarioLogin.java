package com.ApiCrdb.crdb.DTO;

import com.ApiCrdb.crdb.Entidades.Usuario;

public class UsuarioLogin {
	private String email;
	private String senha;
	
	
	
	public UsuarioLogin() {}
	
	
	
	public UsuarioLogin(Usuario usuario) {
		super();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
