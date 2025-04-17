package com.nezztech.generic.generico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nezztech.generic.generico.service.IGenericService;
import com.nezztech.generic.model.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * CONTROLLER
 * 
 * @author ITTIVA
 * 
 */
@Slf4j
public abstract class GenericController<T, Long> {

	protected final IGenericService<T, Long> service;

	public GenericController(IGenericService<T, Long> service) {
		this.service = service;
	}

	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@GetMapping(value = "/consultaTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> consultaTodos() {

		log.info("-");
		log.info("INICIA CONSULTA TODOS !!!");

		ResponseDTO responseDTO = service.consultaTodos();

		log.info("TERMINA CONSULTA TODOS !!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @param id
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@GetMapping(value = "/consultaPorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> consultaPorId(@PathVariable Long id) {

		log.info("-");
		log.info("INICIA CONSULTA POR ID !!!");

		ResponseDTO responseDTO = service.consultaPorId(id);

		log.info("TERMINA CONSULTA POR ID!!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @param id
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@DeleteMapping(value = "/eliminarPorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long id) {

		log.info("-");
		log.info("INICIA ELMINA POR ID !!!");

		ResponseDTO responseDTO = service.deleteById(id);

		log.info("TERMINA ELIMINA POR ID!!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @param ejemploEntity
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> guardar(@RequestBody T entidad) {

		log.info("-");
		log.info("INICIA ALTA !!!");

		ResponseDTO responseDTO = service.guardar(entidad);

		log.info("TERMINA ALTA !!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * 
	 * @author ITTIVA
	 * 
	 * @param entidad
	 * @return ResponseEntity<ResponseDTO> response
	 * 
	 */
	@PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> actualizar(@RequestBody T entidad) {

		log.info("-");
		log.info("INICIA ACTUALIZA !!!");

		ResponseDTO responseDTO = service.actualizar(entidad);

		log.info("TERMINA ACTUALIZA !!!");
		log.info("-");

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

}
