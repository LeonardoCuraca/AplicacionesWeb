package com.aplicaciones.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.aplicaciones.app.models.Proveedor;
import com.aplicaciones.app.services.ProveedorService;

@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ProveedorController {
	
	@Autowired
	@Qualifier("cliente")
	ProveedorService clienteService;
	
	@RequestMapping("/listar")
	public String listar (Model model ) {
		List<Proveedor> clientes =clienteService.listar();
		model.addAttribute("clientes",clientes);
		model.addAttribute("titulo","Lista de Clientes");
		return "clienteListar";
	}
	
	@RequestMapping("/form")
	public String formulario (Map<String, Object> model) {

		Proveedor cliente = new Proveedor();
		model.put("cliente",cliente);
		model.put("btn", "Crear Cliente");
		return "clienteForm";
	}
	
	@RequestMapping("/form/{id}")
	public String actualizar (@PathVariable("id") Long id,Model model) {
		model.addAttribute("cliente",clienteService.buscar(id));
		model.addAttribute("btn","Actualiza Registro");
		return "clienteForm";
	}
	
	@RequestMapping(value="/insertar" ,method=RequestMethod.POST )
	public String guardar(@Valid Proveedor cliente,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("ERROR","Error al enviar registro");
			cliente = new Proveedor();
			model.addAttribute("cliente",cliente);
			model.addAttribute("btn", "Crear Cliente");
			return "clienteForm";
		}else {
		clienteService.guardar(cliente);
		return "redirect:/clientes/listar";
		}
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		clienteService.eliminar(id);
		return"redirect:/clientes/listar";
	}	
}
