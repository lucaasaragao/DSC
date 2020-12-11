package com.ApiCrdb.crdb.Excecoes;

public class UsuarioExcecao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UsuarioExcecao(String msg) {
		super(msg);
	}
	public UsuarioExcecao() {}

}
