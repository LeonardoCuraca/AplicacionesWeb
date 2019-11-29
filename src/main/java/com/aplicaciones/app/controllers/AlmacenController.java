package com.aplicaciones.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aplicaciones.app.models.Producto;
import com.aplicaciones.app.models.Almacen;
import com.aplicaciones.app.services.ProveedorService;
import com.aplicaciones.app.services.ProductoService;
import com.aplicaciones.app.services.AlmacenService;

@Controller
@RequestMapping("/ventas")
@SessionAttributes("venta")
public class AlmacenController {
	@Autowired
	@Qualifier("almacen")
	AlmacenService almacenService;
	
	@Autowired
	@Qualifier("proveedor")
	ProveedorService proveedorService;
	
	@Autowired
	@Qualifier("producto")
	ProductoService productoService;
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Almacen> ventas = almacenService.listar();
		model.addAttribute("ventas",ventas);
		model.addAttribute("titulo","Lista de Ventas");
		return "ventaListar";
	}
	
	@RequestMapping("/form")
	public String formulario(Model model) {
		Almacen venta= new Almacen();
		model.addAttribute("venta", venta);
		model.addAttribute("productos", productoService.listar());
		model.addAttribute("clientes", clienteService.listar());
		model.addAttribute("btn", "Registrar Venta");
		return "ventaForm";
	}
	@RequestMapping(value="/insertar",method=RequestMethod.POST)
	public String guardar(@Valid Almacen venta, Model model) {
		try {
			String id =venta.getProducto();
			Producto pro = productoService.buscar(id);

			if(venta.getCantidad() <= pro.getCantidad()) {
				int diferencia=pro.getCantidad()-venta.getCantidad();
				pro.setCantidad(diferencia);
				double total = pro.getPrecio() * venta.getCantidad();
				venta.setTotal(total);
				
				productoService.guardar(pro);
				ventaService.guardar(venta);
			}else {
				model.addAttribute("ERROR", "No hay stock para este producto, solo tenemos un stock de: "+pro.getCantidad());
				venta= new Almacen();
				model.addAttribute("venta", venta);
				model.addAttribute("productos", productoService.listar());
				model.addAttribute("clientes", clienteService.listar());
				model.addAttribute("btn", "Registrar Venta");
				return "ventaForm";
			
			}
			
			
		} catch (Exception e) {
		}
		return "redirect:/ventas/listar";
	}
	
	@RequestMapping("/form/{id}")
	public String actualizar (@PathVariable("id") Long id,Model model) {
		model.addAttribute("venta",ventaService.buscar(id));
		model.addAttribute("productos", productoService.listar());
		model.addAttribute("clientes", clienteService.listar());
		model.addAttribute("btn","Actualiza Registro");
		return "ventaForm";
	}

	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		ventaService.eliminar(id);
		return "redirect:/ventas/listar";
	}
}
