package com.ApiCrdb.crdb.Excecoes;

public class ComentarioExcecao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public ComentarioExcecao(String msg) {
		super(msg);
	}
	public ComentarioExcecao() {}

}
