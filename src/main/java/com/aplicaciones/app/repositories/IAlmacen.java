package com.aplicaciones.app.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicaciones.app.models.Almacen;

@Repository
public interface IAlmacen extends JpaRepository<Almacen, Serializable>{

	public abstract Almacen findById(Long id);
	
}
