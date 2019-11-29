package com.aplicaciones.app.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicaciones.app.models.Producto;
import com.aplicaciones.app.models.Almacen;

@Repository
public interface IProducto extends JpaRepository<Producto, Serializable>{

	public abstract Producto findById(Long id);
	public abstract Producto findByNombre(String producto);
}
