package com.ApiCrdb.crdb.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiCrdb.crdb.DTO.DisciplinaPorIdNome;
import com.ApiCrdb.crdb.Entidades.Disciplina;
import com.ApiCrdb.crdb.Excecoes.DisciplinaExcecao;
import com.ApiCrdb.crdb.Servico.DisciplinaServico;

@RequestMapping("/disciplina")
@RestController
public class DisciplinaControlador {
	
	@Autowired
	private DisciplinaServico disciplinaServico;
	
	@GetMapping("/prefixoDisciplina")
	public ResponseEntity<List<DisciplinaPorIdNome>> disciplinaComPrefixo (@RequestBody Disciplina disciplina){
		try {
		String prefixo = disciplina.getNome();
		
		return new ResponseEntity<List<DisciplinaPorIdNome>>(disciplinaServico.buscaPrefixoDisciplina(prefixo),HttpStatus.OK);
		}
		catch(DisciplinaExcecao e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/hankingNotas")
	public ResponseEntity<List<Disciplina>> hankingLikes(){
		try {
			return new ResponseEntity<>(disciplinaServico.hankingNotas(),HttpStatus.OK);
		}
		catch(DisciplinaExcecao e) {
			return ResponseEntity.badRequest().build();
		}
		
		
		
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Disciplina> buscar(@PathVariable int id){
		try {
			return new ResponseEntity<Disciplina>(disciplinaServico.buscar(id),HttpStatus.OK);
		}
		catch(DisciplinaExcecao e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@PutMapping("/like/{Id}")
	public ResponseEntity<Disciplina> like(@PathVariable int Id, @RequestHeader("Authorization")String token){
		try {
			return new ResponseEntity<Disciplina>(disciplinaServico.DarLike(Id),HttpStatus.OK);
			
		}
		catch(DisciplinaExcecao e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	@PutMapping("/atualizarNota/{Id}")
	public ResponseEntity<Disciplina>AtualizacaoDeNota(@PathVariable int Id,@RequestBody Disciplina disciplina){
		double nota = disciplina.getNota();
		try {
			return new ResponseEntity<Disciplina>(disciplinaServico.atualizarNota(nota, Id),HttpStatus.OK);
		}
		catch(DisciplinaExcecao e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
