package com.lab1.controller;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab1.model.*;
import com.lab1.service.*;
import com.sun.el.parser.ParseException;

@RestController

@RequestMapping("v1/api/disciplinas") //Caminho padrao 
public class DisciplinaController {
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@GetMapping
	public ResponseEntity<?> getDisciplinas(){
		
		return new ResponseEntity<>(disciplinaService.getDisciplinas(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> postDisciplinas(@RequestBody Disciplina disciplina){
		disciplina.setId(disciplinaService.getDisciplinas().size());
		disciplinaService.createDisciplina(disciplina);
		
		return new ResponseEntity<>(disciplina, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDisciplinaforID(@PathVariable int id){
		Disciplina a = disciplinaService.getDisciplinaforID(id);
		if(a == null) {
			
			return new ResponseEntity<>(a, HttpStatus.OK);
		}else {
			
			return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/ranking")
	public ResponseEntity<?> getRankedDisciplina(){
		
		return new ResponseEntity<>(disciplinaService.getOrdered(), HttpStatus.OK); 
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDisciplina(@PathVariable int id){
		Disciplina a = disciplinaService.getDisciplinaforID(id);
		disciplinaService.deleteDisciplina(id);
		
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
	/*
	@PatchMapping(value = "/{id}/nome", consumes = "application/json")
	public ResponseEntity<?> putDisciplinaNome(@PathVariable int id, @RequestBody String json){
		JSONParser parser = new JSONParser();
		JSONObject  jsonB = null;
		try {
			
			jsonB = (JSONObject) parser.parse(json);
		}catch (ParseException e) {
			
			return new ResponseEntity<>("Nao pode ser modificado", HttpStatus.BAD_REQUEST);
		}
		
		Disciplina a = disciplinaService.getDisciplinaforID(id);
		if(a == null) {
			
			return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
		}else {
			a.setNome(jsonB.get("nome").toString());
			
			return new ResponseEntity<>(a, HttpStatus.OK);
		}
	}
	
	@PatchMapping ("/{id}/nota")
	public ResponseEntity<?> putDisciplinaNota(@PathVariable int id, @RequestBody String json) {
        JSONParser parser = new JSONParser();
        JSONObject jsonB = null;
        try {
            jsonB = (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            return new ResponseEntity<>("Nao pode ser modificado.", HttpStatus.BAD_REQUEST);
        }
        Disciplina a = disciplinaService.getDisciplinaforID(id);
        if (a == null) {
            
        	return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
        } else {
            a.setNota((double) jsonB.get("nota"));
            
            return new ResponseEntity<>(a, HttpStatus.OK);
        }
		
	}*/
}
