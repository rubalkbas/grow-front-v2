package com.nezztech.generic.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nezztech.generic.generico.controller.GenericController;
import com.nezztech.generic.model.dto.GenericEstatusDTO;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.EjemploEntity;
import com.nezztech.generic.service.IEjemploService;

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
@RequestMapping("/api/ejemplo")// todos los controladores deben empezar con /api
public class EjemploController extends GenericController<EjemploEntity, Long> { // extiende los metodos genericos
	
	private final IEjemploService  ejemploService; // se pone cuando se pondran endpoints extras que se ocupen de los que no estan en los genericos

	public EjemploController(IEjemploService  service) { // se pone para tener endpoints genericos
		super(service);
		this.ejemploService = service;
	}
	
	
	/**
	 * Cualquier estructura a generar copiarla del controllador generico y usarla para los nuevos metodos requeridos, para tener todo homologado.
	 */
	
	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @param entidad
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@PutMapping(value = "/actualizarbyEstatus/{idEjemplo}/{estatus}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> actualizarbyEstatus(@RequestBody GenericEstatusDTO ejemploDTO) {

		log.info("-");
		log.info("INICIA ACTUALIZA !!!");

		ResponseDTO responseDTO = ejemploService.actualizarbyEstatus(ejemploDTO);

		log.info("TERMINA ACTUALIZA !!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

}
