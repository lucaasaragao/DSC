package com.lab2.roteiro2.controladores;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab2.roteiro2.entidades.Disciplina;
import com.lab2.roteiro2.servicos.DisciplinaService;

import net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.ForLoadedReturnType;
/**
 * @author Lucas Aragao 
 * 
 * GET /api/disciplinas Retorna um JSON (com campos id, nome) com todas as disciplinas inseridas no sistema e código 200.
 * 
 * GET /api/disciplinas/{id} Retorna um JSON que representa a disciplina completa (id, nome, nota, likes e comentarios) 
 *     cujo identificador único é id e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha 
 *     sido encontrado.
 * 
 * PUT /api/disciplinas/likes/{id} Incrementa em um o número de likes da disciplina cujo identificador é id. Retorna a disciplina que 
 *     foi atualizada (incluindo o id, nome e likes) e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não 
 *     tenha sido encontrado.
 *     
 * PUT /api/disciplinas/nota/{id} Atualiza a nota da disciplina de identificador id no sistema. No corpo da requisição HTTP deve estar
 *     um JSON com uma nova nota atribuída à disciplina. A nova nota da disciplina é a média aritmética da nota anterior da disciplina
 *     e da nova nota passada nesta chamada. Se for a primeira nota sendo adicionada então esta nota é a que vai valer para a disciplina.
 *     Retorna a disciplina que foi atualizada (incluindo o id, nome e nota) e código 200. Ou não retorna JSON e código 404 (not found) 
 *     caso o id passado não tenha sido encontrado.
 *     
 * PUT /api/disciplinas/comentarios/{id} Insere um novo comentário na disciplina de identificador id. No corpo da requisição HTTP deve 
 *     estar um JSON com o novo comentário (chave “comentario”) a ser adicionado na disciplina a ser atualizada no sistema. Retorna a 
 *     disciplina que foi atualizada (incluindo o id, nome e os comentarios atualizados) e código 200. Ou não retorna JSON e código 404 
 *     (not found) caso o id passado não tenha sido encontrado.
 *       
 */

@RestController
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	// Retorna todas as disciplinas
	@GetMapping("/api/disciplinas")
	public ResponseEntity<List<Disciplina>> getAllDisciplinas(){
		return new ResponseEntity<List<Disciplina>>(disciplinaService.getAllDisciplinas(), HttpStatus.OK);
	}
	
	// Retorna disciplinas pelo ID
	@GetMapping("/api/disciplina/{id}")
	public ResponseEntity<Disciplina> getDisciplinaForId(@PathVariable Integer id){
		try {
			return new ResponseEntity<Disciplina>(disciplinaService.getDisciplinaForId(id), HttpStatus.OK);
		}catch (NoSuchElementException aiobe){
			return new ResponseEntity<Disciplina>(new Disciplina(null), HttpStatus.NOT_FOUND);
		}
	}
	
	// Likes nas disciplinas
	@PutMapping("/api/disciplinas/like/{id}")
	public ResponseEntity<Disciplina> setLikeDisciplinaForId(@PathVariable Integer id){
		try {
			return new ResponseEntity<Disciplina>(disciplinaService.getDisciplinaForId(id), HttpStatus.OK);
		} catch (NoSuchElementException aiobe){
			return new ResponseEntity<Disciplina>(new Disciplina(null), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/disciplinas/nota/{id}")
	public ResponseEntity<Disciplina> uppdateNoteDisciplina(@PathVariable Integer id, Double nota){
		try {
			return new ResponseEntity<Disciplina>(disciplinaService.uppdateNoteDisciplina(id, nota), HttpStatus.OK);
		}catch(NoSuchElementException aiobe){
			return new ResponseEntity<Disciplina>(new Disciplina(null), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/disciplinas/comentarios/{id}")
	public ResponseEntity<Disciplina> uppdateCommentDisciplina(@PathVariable Integer id, String comentario){
		try {
			return new ResponseEntity<Disciplina>(disciplinaService.uppdateCommentDisciplina(id, comentario), HttpStatus.OK);
		}catch(NoSuchElementException aiobe) {
			return new ResponseEntity<Disciplina>(new Disciplina(null), HttpStatus.NOT_FOUND);
		}
	}
}













