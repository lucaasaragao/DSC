package com.ApiCrdb.crdb.Servico;

import java.util.Date;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ApiCrdb.crdb.DTO.UsuarioLogin;
import com.ApiCrdb.crdb.Entidades.Usuario;
import com.ApiCrdb.crdb.Repositorios.UsuarioRepositorio;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JWTServico {

	@Autowired
	private UsuarioRepositorio<Usuario, String> usuarioBD;

	public static final String TOKEN_KEY = "ja pode sair?";

	public LoginServico autentica(UsuarioLogin usuario) {
		String mensagemError = "Usuario e/ou senha Inválidos";
		Optional<Usuario> optUsuario = usuarioBD.findByEmail(usuario.getEmail());
		if (optUsuario.isPresent() && usuario.getSenha().equals(optUsuario.get().getSenha())) {
			return new LoginServico(gerarToken(usuario));
		}
		return new LoginServico(mensagemError);

	}

	private String gerarToken(UsuarioLogin usuario) {
		String token = Jwts.builder().setSubject(usuario.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)).compact();
		return token;
	}

	public Optional<String> recuperarUsuario(String cabecalhoAutorizacao) {
		if (cabecalhoAutorizacao == null || !cabecalhoAutorizacao.startsWith("Bearer")) {
			throw new SecurityException();
		}
		String token = cabecalhoAutorizacao.substring(com.ApiCrdb.crdb.Filtros.filtroToken.TOKEN_INDEX);

		String subject = null;

		try {
			subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new SecurityException("Token Invalido ou inspirado");
		}
		return Optional.of(subject);

	}
	public Optional<String> recuperarComentario(String cabecalhoAutorizacao) {
		if (cabecalhoAutorizacao == null || !cabecalhoAutorizacao.startsWith("Bearer")) {
			throw new SecurityException();
		}
		String token = cabecalhoAutorizacao.substring(com.ApiCrdb.crdb.Filtros.filtroToken.TOKEN_INDEX);

		String subject = null;

		try {
			subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new SecurityException("Token Invalido ou inspirado");
		}
		return Optional.of(subject);

	}
	public String getUsuarioId(String autorizacao) {
		Optional<String> emailUsuario = recuperarUsuario(autorizacao);
		if(emailUsuario.isEmpty())
			throw new SecurityException();
		return emailUsuario.get();
	}
}
