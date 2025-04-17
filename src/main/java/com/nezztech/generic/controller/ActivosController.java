package com.nezztech.generic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nezztech.generic.generico.controller.GenericController;
import com.nezztech.generic.model.dto.GenericEstatusDTO;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ActivosEntity;
import com.nezztech.generic.service.IActivosService;

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
@RequestMapping("/api/activos")
public class ActivosController extends GenericController<ActivosEntity, Long>{
	
	private final IActivosService iActivosService; 

	public ActivosController(IActivosService  service) { 
		super(service);
		this.iActivosService = service;
	}
	
	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @param activo
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@GetMapping(value = "/consultaPorActivo/{activo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> consultaPorActivo(@PathVariable String activo) {

		log.info("-");
		log.info("INICIA CONSULTA POR activo !!!");

		ResponseDTO responseDTO = iActivosService.consultaPorActivo(activo);

		log.info("TERMINA CONSULTA POR activo !!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}
	
	
	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @param 
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@PutMapping(value = "/actualizarbyEstatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> actualizarbyEstatus(@RequestBody GenericEstatusDTO genericEstatusDTO) {

		log.info("-");
		log.info("INICIA ACTUALIZA !!!");

		ResponseDTO responseDTO = iActivosService.actualizarbyEstatus(genericEstatusDTO);

		log.info("TERMINA ACTUALIZA !!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

}
