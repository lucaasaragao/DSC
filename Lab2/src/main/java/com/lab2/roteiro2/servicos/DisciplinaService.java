package com.lab2.roteiro2.servicos;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab2.roteiro2.entidades.Disciplina;
import com.lab2.roteiro2.repositorios.DisciplinaRepository;

@Service
public class DisciplinaService {

	
	@Autowired
	private DisciplinaRepository<Disciplina, Integer> disciplinaDAO;
	
	//
	public List<Disciplina>getAllDisciplinas(){
		return disciplinaDAO.findAll();
	}
	//
	public Disciplina getDisciplinaForId(int id) {
		if (disciplinaDAO.count() == 0 || id <= 0 || id > (int) disciplinaDAO.count()) {
			throw new NoSuchElementException();
		}
		if (disciplinaDAO.findById(id).isEmpty()) {
			throw new NoSuchElementException();
		}
	
		Disciplina disciplina = disciplinaDAO.findById(id).get();
		return disciplina;
	}
	// 
	public Disciplina setLikeDisciplinaForId(int id) {
		if(disciplinaDAO.count() == 0 || id <= 0 || id > (int) disciplinaDAO.count()) {
			throw new NoSuchElementException();
		}
		if (disciplinaDAO.findById(id).isEmpty()) {
			throw new NoSuchElementException();
		}
	
		int temp = (disciplinaDAO.findById(id).get().getLikes()) + 1;
		disciplinaDAO.findById(id).get().setLikes(temp);
		Disciplina disciplina = disciplinaDAO.findById(id).get();
		return disciplina;
	}
	//
	public Disciplina uppdateNoteDisciplina(int id, double nota) {
		
		if(disciplinaDAO.count() == 0 || id <= 0 || id > (int) disciplinaDAO.count()) {
			throw new NoSuchElementException();
		}
		if(disciplinaDAO.findById(id).isEmpty()) {
			throw new NoSuchElementException();
		}
		if(disciplinaDAO.findById(id).get().getNota() == 0) {
			disciplinaDAO.findById(id).get().setNota(nota);
		}
		
		Disciplina disciplina = disciplinaDAO.findById(id).get();
		return disciplina;
	}
	
	public Disciplina uppdateCommentDisciplina(int id, String comentario) {
		
		if (disciplinaDAO.count() == 0 || id <= 0 || id > (int) disciplinaDAO.count()) {
			throw new NoSuchElementException();
		}
		if (disciplinaDAO.findById(id).isEmpty()){
			throw new NoSuchElementException();
		}
		
		String temp = disciplinaDAO.findById(id).get().getComentarios();
		temp += " /n " + comentario;
		disciplinaDAO.findById(id).get().setComentarios(temp);
		Disciplina disciplina = disciplinaDAO.findById(id).get();
		return disciplina;
	}
	
	
	
}
