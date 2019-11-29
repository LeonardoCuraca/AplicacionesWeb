package com.aplicaciones.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicaciones.app.models.Almacen;
import com.aplicaciones.app.repositories.IAlmacen;

@Service("almacen")
public class AlmacenService {

	@Autowired
	IAlmacen iAlmacen;
	
	public void guardar(Almacen almacen) {
		
		iAlmacen.save(almacen);
	}
	
	public List<Almacen> listar(){
		return iAlmacen.findAll();
	}
	
	public Almacen buscar(Long id) {
		return iAlmacen.findById(id);
	}
	
	public boolean eliminar(Long id) {
		try {
			iAlmacen.delete(iAlmacen.findById(id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
