package com.aplicaciones.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicaciones.app.models.Proveedor;
import com.aplicaciones.app.repositories.IProveedor;

@Service("proveedor")
public class ProveedorService{
	
	@Autowired
	private IProveedor iProveedor;
	
	public void guardar(Proveedor proveedor) {
		iProveedor.save(proveedor);
	}
	
	public List<Proveedor> listar (){
		 return iProveedor.findAll();
	}
	
	public  Proveedor buscar(Long id) {
		return  iProveedor.findById(id);
	}
	
	public boolean eliminar (Long id) {
		try {
			iProveedor.delete(iProveedor.findById(id));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}	
	}
}
