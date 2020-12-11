package com.ApiCrdb.crdb.Servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiCrdb.crdb.DTO.UsuarioDTO;
import com.ApiCrdb.crdb.Entidades.Usuario;
import com.ApiCrdb.crdb.Excecoes.UsuarioExcecao;
import com.ApiCrdb.crdb.Excecoes.UsuarioExistente;
import com.ApiCrdb.crdb.Repositorios.UsuarioRepositorio;


@Service
public class UsuarioServico {
	
	@Autowired
	private UsuarioRepositorio<Usuario, String> usuarioBD;
	
	private JWTServico jwtServico;
	
	
	public UsuarioDTO criarUsuario(Usuario usuario) {
		if(!usuario.isValid()) {
			throw new UsuarioExcecao();
			
		}
		if(usuarioBD.existsById(usuario.getEmail())) {
			throw new UsuarioExistente();
		}
		usuarioBD.save(usuario);
		return new UsuarioDTO(usuario);
		
	}
	public UsuarioDTO deletaUsuario(String cabecalho) {
		//le o token 
		Optional<String> usuarioId = jwtServico.recuperarUsuario(cabecalho);
		
		Usuario usuario = validarUsuario(usuarioId);
		
		usuarioBD.delete(usuario);
		return new UsuarioDTO(usuario);
	}
	public Usuario validarUsuario(Optional<String> id) {
		if(id.isEmpty()) {
			throw new UsuarioExcecao();
		}
		Optional<Usuario> usuario = usuarioBD.findByEmail(id.get());
		if(usuario.isEmpty()) {
			throw new UsuarioExcecao();
		}
		return usuario.get();
		
	}
	public Usuario getUsuario(String email) {
		Optional<Usuario> usuarioOpt = usuarioBD.findByEmail(email);
		if (usuarioOpt.isEmpty())
			throw new UsuarioExcecao();
		return usuarioOpt.get();

	}

}
