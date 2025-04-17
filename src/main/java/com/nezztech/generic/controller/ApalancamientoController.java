package com.nezztech.generic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nezztech.generic.generico.controller.GenericController;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ApalancamientoEntity;
import com.nezztech.generic.service.IApalancamientoService;

import lombok.extern.slf4j.Slf4j;

/**
 * CONTROLLER
 * 
 * @author 
 * 
 */
@Slf4j
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/apalancamiento")
public class ApalancamientoController extends GenericController<ApalancamientoEntity, Long>{
	
	private final IApalancamientoService iApalancamientoService; 

	public ApalancamientoController(IApalancamientoService  service) { 
		super(service);
		this.iApalancamientoService = service;
	}
	
	
	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @param id
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@GetMapping(value = "/consultaPorIdCliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> consultaPorIdCliente(@PathVariable Long id) {

		log.info("-");
		log.info("INICIA CONSULTA POR activo !!!");

		ResponseDTO responseDTO = iApalancamientoService.consultaPorIdCliente(id);

		log.info("TERMINA CONSULTA POR activo !!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}


}
