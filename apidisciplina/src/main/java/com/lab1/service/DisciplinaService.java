package com.lab1.service;

import com.lab1.model.Disciplina;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {
	
	List<Disciplina> disciplinas;
	
	public DisciplinaService() {
		this.disciplinas = new ArrayList();
	}
	
	public List<Disciplina> getDisciplinas(){
		
		return this.disciplinas;
	}
	
	// Recuperando disciplina pelo ID
	public Disciplina getDisciplinaforID(int id) {
		for (Disciplina disciplina: this.disciplinas) {
			if (disciplina.getId() == id) {
				
				return disciplina;
			}
		}
		
		return null;
	}
	//Criando Disciplina
	public boolean createDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
		return true;
	}
	//Removendo Disicplina pelo ID
	public boolean deleteDisciplina(int id) {
		Disciplina disciplina = null;
		for(Disciplina a: this.disciplinas) {
			if(a.getId() == id) {
				disciplina = a;
			}
		}
		
		return false;
	}
	
	//Retornando a lista de disciplinas da maior para menor
	 public List<Disciplina> getOrdered() {
        Collections.sort(disciplinas, new Comparator<Disciplina>() {
            @Override
            public int compare(Disciplina disciplina, Disciplina t1) {
                if (t1.getNota() == disciplina.getNota()) {
                    return 0;
                }
                else if (t1.getNota() < disciplina.getNota()) {
                    return -1;
                }
                return 1;
            }
        });
        return disciplinas;
    }
}
