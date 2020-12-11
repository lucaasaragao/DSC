package com.ApiCrdb.crdb.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ApiCrdb.crdb.DTO.UsuarioLogin;
import com.ApiCrdb.crdb.Excecoes.UsuarioExcecao;
import com.ApiCrdb.crdb.Servico.JWTServico;
import com.ApiCrdb.crdb.Servico.LoginServico;

@RestController
public class LoginControlador {
	
	@Autowired
	private JWTServico jwtServico;

	@PostMapping("/autorizacao/login")
	public ResponseEntity<LoginServico> autenticarUsuario(@RequestBody UsuarioLogin usuario) {
		try {
			return new ResponseEntity<LoginServico>(jwtServico.autentica(usuario), HttpStatus.OK);
		} catch (UsuarioExcecao e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

}
