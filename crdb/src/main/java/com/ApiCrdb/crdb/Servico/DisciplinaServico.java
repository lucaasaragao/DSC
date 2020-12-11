package com.ApiCrdb.crdb.Servico;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiCrdb.crdb.DTO.DisciplinaPorIdNome;
import com.ApiCrdb.crdb.Entidades.Disciplina;
import com.ApiCrdb.crdb.Excecoes.DisciplinaExcecao;
import com.ApiCrdb.crdb.Repositorios.DisciplinaRepositorio;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class DisciplinaServico {
	
	@Autowired
	private DisciplinaRepositorio<Disciplina, Integer> disciplinaBD;

	

	@Autowired
	private ComentarioServico comentarioServico;



	public DisciplinaServico() {

	}

	@PostConstruct
	public void initDisciplinas() {

		try {
			if (disciplinaBD.count() == 96L) {
				System.out.println("Já existe 96 elementos");

			} else {

				ObjectMapper mapper = new ObjectMapper();
				TypeReference<List<Disciplina>> tipoDeDados = new TypeReference<List<Disciplina>>() {
				};
				InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplina.json");
				List<Disciplina> disciplina = mapper.readValue(inputStream, tipoDeDados);

				System.out.println("Disciplinas salva no Banco De Dados");
				LocalTime now = LocalTime.now();

				System.out.println(now);

				this.disciplinaBD.saveAll(disciplina);
				
			}
		} catch (IOException e) {
			System.out.println("não foi possivel salvar " + e.getMessage());

		}

	}

	public List<DisciplinaPorIdNome> buscaPrefixoDisciplina(String expresao) {
		List<Disciplina> listaDePrefixo = new ArrayList<>();

		for (Disciplina disciplina : this.disciplinaBD.findAll()) {
			if (disciplina.getNome().startsWith(expresao)) {
				listaDePrefixo.add(disciplina);

			}

		}
		List<DisciplinaPorIdNome> listaDTO = listaDePrefixo.stream().map(x -> new DisciplinaPorIdNome(x)).collect(Collectors.toList());
		return listaDTO;

	}

	public Disciplina buscar(int disciplinaId) {
		Optional<Disciplina> disciplina = disciplinaBD.findById(disciplinaId);
		if (disciplina.isPresent()) {
			disciplina.get().setComentario(comentarioServico.ComentariosDeDisciplina(disciplinaId));
			return disciplina.get();

		}

		throw new DisciplinaExcecao("Não Encontrado Id Informado");
	}

	public Disciplina atualizarNota(double nota, int id) {

		ArrayList<Disciplina> lista = new ArrayList<>();
		Disciplina disciplina01 = this.getOne(id);

		if (disciplina01.getId() == id) {
			if (disciplina01.getNota() == 0) {
				disciplina01.setNota(nota);
				disciplinaBD.save(disciplina01);
				lista.add(disciplina01);

			} else {
				disciplina01.setNota((disciplina01.getNota() + nota) / 2);
				disciplinaBD.save(disciplina01);
				lista.add(disciplina01);
			}

		}
		listaDeNotas(lista);
		return disciplina01;
	}

	public List<Double> listaDeNotas(List<Disciplina> notas) {
		List<Double> listaNotas = new ArrayList<>();

		for (Disciplina d : notas) {
			listaNotas.add(d.getNota());
		}
		return listaNotas;

	}

	public Disciplina getOne(int id) throws DisciplinaExcecao {
		Optional<Disciplina> disciplina = this.disciplinaBD.findById(id);
		if (disciplina != null)
			return disciplina.get();
		throw new DisciplinaExcecao("Disciplina não encontrada");
	}

	public List<Disciplina> lista() {
		return this.disciplinaBD.findAll();

	}

	

	public Disciplina DarLike(int id) throws DisciplinaExcecao {
		Optional<Disciplina> disciplinaOptional = this.disciplinaBD.findById(id);
		Disciplina disciplina = null;
		if (disciplinaOptional != null) {
			disciplina = disciplinaOptional.get();
			
			if (disciplina.getLikes() == 0) {
				disciplina.setLikes(disciplina.getLikes() + 1);
				
			}
			else if(disciplina.getLikes() == 1) {
				disciplina.setLikes(0);
			}
			this.disciplinaBD.save(disciplina);
			return disciplina;
		}
		throw new DisciplinaExcecao("Disciplina Não Encontrada");

	}

	public Disciplina getOneNome(String nome) {
		Optional<Disciplina> disciplina = this.disciplinaBD.findByNome(nome);
		if (disciplina != null) {
			return disciplina.get();
		}
		throw new DisciplinaExcecao("Disciplina não encontrada");
	}

	public List<Disciplina> hankingNotas() {
		List<Disciplina> listaDisciplina = this.disciplinaBD.findByOrderByNotaDesc();
		return listaDisciplina;
	}

}
