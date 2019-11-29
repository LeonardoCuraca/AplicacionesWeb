package com.aplicaciones.app.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicaciones.app.models.Proveedor;

@Repository
public interface IProveedor extends JpaRepository<Proveedor, Serializable>{
	
	public abstract Proveedor findById(Long id);
}
