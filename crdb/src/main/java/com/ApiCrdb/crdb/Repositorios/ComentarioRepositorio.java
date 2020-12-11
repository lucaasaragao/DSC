package com.ApiCrdb.crdb.Repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ApiCrdb.crdb.Entidades.Comentario;


@Repository
public interface ComentarioRepositorio<T,Id extends Serializable> extends JpaRepository<Comentario, Long> {
	
	
}
