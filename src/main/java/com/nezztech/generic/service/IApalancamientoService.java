package com.nezztech.generic.service;

import com.nezztech.generic.generico.service.IGenericService;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ApalancamientoEntity;

/**
 * INTERFACE DE SERVICE
 * 
 * @author 
 */
public interface IApalancamientoService extends IGenericService<ApalancamientoEntity, Long>{
	
	/**
	 * Consulta por ID.
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO consultaPorIdCliente(Long id);
	
}
