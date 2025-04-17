package com.nezztech.generic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nezztech.generic.generico.controller.GenericController;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ApalancamientoEntity;
import com.nezztech.generic.model.entity.ApuestaEntity;
import com.nezztech.generic.model.entity.HistoricoApuestaEntity;
import com.nezztech.generic.service.IApuestaService;

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
@RequestMapping("/api/apuesta")
public class Apuestacontroller {
	
	/**  */
	@Autowired
	private IApuestaService iApuestaClienteService;
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@PostMapping(path = "/consultaApuestasAbiertasClienteID", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> consultaApuestasAbiertasClienteID( @RequestBody ApuestaEntity apuestaClienteEntity ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iApuestaClienteService.consultaApuestasAbiertasClienteID(apuestaClienteEntity);	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@PostMapping(path = "/consultaApuestasCerradasClienteID", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> consultaApuestasCerradasClienteID( @RequestBody ApuestaEntity apuestaClienteEntity ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iApuestaClienteService.consultaApuestasCerradasClienteID(apuestaClienteEntity);	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@PostMapping(path = "/crearApuestas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> crearApuestas( @RequestBody ApuestaEntity apuestaClienteEntity ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iApuestaClienteService.crearApuestas(apuestaClienteEntity);	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}
	
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@PostMapping(path = "/cerrarApuesta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> cerrarApuesta( @RequestBody ApuestaEntity apuestaClienteEntity ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iApuestaClienteService.cerrarApuesta(apuestaClienteEntity);	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@PostMapping(path = "/consultaApuestasHistoricoAbiertasClienteID", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> consultaApuestasHistoricoAbiertasClienteID( @RequestBody HistoricoApuestaEntity historicoApuestaClienteEntity ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iApuestaClienteService.consultaApuestasHistoricoAbiertasClienteID(historicoApuestaClienteEntity);	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@PostMapping(path = "/actualizarApuesta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> actualizarApuesta( @RequestBody ApuestaEntity apuestaClienteEntity ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iApuestaClienteService.actualizarApuesta(apuestaClienteEntity);	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}
	
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@PostMapping(path = "/consultaApalancamientoClienteID", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> consultaApalancamientoClienteID( @RequestBody ApalancamientoEntity apalancamiento ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iApuestaClienteService.consultaApalancamientoClienteID(apalancamiento);	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@PostMapping(path = "/actualizarApalancamiento", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> actualizarApalancamiento( @RequestBody ApalancamientoEntity apalancamiento ) {
		
		log.info("-");
		log.info("INICIA  !!!");
		
		ResponseDTO responseDTO = iApuestaClienteService.actualizarApalancamiento(apalancamiento);	
		
		log.info("TERMINA !!!");
		log.info("-");
		
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}

}
