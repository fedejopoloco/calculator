package com.ma.valuacion.orden.aplicacion;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.ma.valuacion.orden.dominio.Orden;
import com.ma.valuacion.orden.repositorio.OrdenRepository;
import com.ma.valuacion.shared.dominio.UserCase;

@UserCase
public class ConsultarOrden {

	private static Logger logger = Logger.getLogger(ConsultarOrden.class.getName());

	
	@Autowired
	private OrdenRepository ordenRepository;

		
	public Orden obtenerOrden(Long idOrden) {
		return ordenRepository.obtenerOrden(idOrden);
	}	

}