package com.ApiCrdb.crdb.Excecoes;

public class UsuarioExistente extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UsuarioExistente(String msg) {
		super(msg);
	}
	public UsuarioExistente() {}
}
