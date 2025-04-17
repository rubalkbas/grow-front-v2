package com.nezztech.generic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nezztech.generic.generico.controller.GenericController;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.UsuariosEntity;
import com.nezztech.generic.service.IUsuariosService;

import lombok.extern.slf4j.Slf4j;

/**
 * CONTROLLER
 * 
 * @author ITTIVA
 * 
 */
@Slf4j
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/usuarios")
public class UsuariosController extends GenericController<UsuariosEntity, Long>{
	
	private final IUsuariosService iUsuariosService; 

	public UsuariosController(IUsuariosService  service) { 
		super(service);
		this.iUsuariosService = service;
	}
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@GetMapping(path = "/consultaUsuariosClientes/{idAdmin}")
	public ResponseEntity<ResponseDTO> consultaUsuariosClientes( @PathVariable Long idAdmin ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iUsuariosService.consultaUsuariosClientes( idAdmin );	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}

}
