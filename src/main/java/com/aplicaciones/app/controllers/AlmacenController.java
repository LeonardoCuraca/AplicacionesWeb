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

import com.aplicaciones.app.models.Almacen;
import com.aplicaciones.app.services.AlmacenService;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/almacenes")
@SessionAttributes("almacen")
public class AlmacenController {
	@Autowired
	@Qualifier("almacen")
	AlmacenService almacenService;	
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Almacen> almacenes = almacenService.listar();
		model.addAttribute("almacenes",almacenes);
		model.addAttribute("titulo","Lista de Almacenes");
		return "almacenListar";
	}
	
	@RequestMapping("/form")
	public String formulario(Model model) {
		Almacen almacen= new Almacen();
		model.addAttribute("almacen", almacen);
		model.addAttribute("btn", "Registrar en el Almacen");
		return "almacenForm";
	}
        
	@RequestMapping(value="/insertar",method=RequestMethod.POST)
	public String guardar(@Valid Almacen almacen,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("ERROR","Error al enviar registro");
                        almacen = new Almacen();
			model.addAttribute("Almacen",almacen);
			model.addAttribute("btn","Crear Almacen");
			return "almacenForm";
		}else {
                    almacenService.guardar(almacen);
                    return "redirect:/almacenes/listar";
		}
	}
	
	@RequestMapping("/form/{id}")
	public String actualizar (@PathVariable("id") Long id,Model model) {
		model.addAttribute("almacen",almacenService.buscar(id));
		model.addAttribute("btn","Actualiza Registro");
		return "almacenForm";
	}

	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		almacenService.eliminar(id);
		return "redirect:/almacenes/listar";
	}
}
