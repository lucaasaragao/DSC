package com.ApiCrdb.crdb.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiCrdb.crdb.DTO.UsuarioDTO;
import com.ApiCrdb.crdb.Entidades.Usuario;
import com.ApiCrdb.crdb.Excecoes.UsuarioExcecao;
import com.ApiCrdb.crdb.Excecoes.UsuarioExistente;
import com.ApiCrdb.crdb.Servico.UsuarioServico;


@RequestMapping("/usuario")
@RestController
public class UsuarioControlador {
	@Autowired
	private UsuarioServico usuarioServico;
	
	
	@PostMapping("/criarUsuario")
	public ResponseEntity<UsuarioDTO> novoUsuario(@RequestBody Usuario usuario){
		try {
			return new ResponseEntity<UsuarioDTO>(usuarioServico.criarUsuario(usuario),HttpStatus.CREATED);
		}
		catch(UsuarioExistente e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		catch(UsuarioExcecao e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	@DeleteMapping("/deletar")
	public ResponseEntity<UsuarioDTO>deletar(@RequestHeader("Authorization")String token){
		try {
			return new ResponseEntity<UsuarioDTO>(usuarioServico.deletaUsuario(token),HttpStatus.OK);
		}
		catch(UsuarioExcecao e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
