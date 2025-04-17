package com.nezztech.generic.service;

import com.nezztech.generic.generico.service.IGenericService;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ApalancamientoEntity;
import com.nezztech.generic.model.entity.ApuestaEntity;
import com.nezztech.generic.model.entity.HistoricoApuestaEntity;

/**
 * INTERFACE DE SERVICE
 * 
 * @author 
 */
public interface IApuestaService {
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO consultaApuestasAbiertasClienteID( ApuestaEntity apuestaClienteEntity );
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO consultaApuestasCerradasClienteID( ApuestaEntity apuestaClienteEntity );
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO crearApuestas( ApuestaEntity apuestaClienteEntity );
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO cerrarApuesta( ApuestaEntity apuestaClienteEntity );
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO consultaApuestasHistoricoAbiertasClienteID( HistoricoApuestaEntity historicoApuestaClienteEntity );


	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO actualizarApuesta( ApuestaEntity apuestaClienteEntity );
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO consultaApalancamientoClienteID( ApalancamientoEntity apalancamiento );
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO actualizarApalancamiento( ApalancamientoEntity apalancamiento );

}
