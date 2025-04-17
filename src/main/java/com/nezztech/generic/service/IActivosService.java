package com.nezztech.generic.service;

import com.nezztech.generic.generico.service.IGenericService;
import com.nezztech.generic.model.dto.GenericEstatusDTO;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ActivosEntity;

/**
 * INTERFACE DE SERVICE
 * 
 * @author 
 */
public interface IActivosService extends IGenericService<ActivosEntity, Long> {
	
	/**
	 * Consulta por ID.
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO consultaPorActivo(String activo);
	
	/**
	 * Actualizar
	 * 
	 * @return ResponseDTO
	 */
	ResponseDTO actualizarbyEstatus(GenericEstatusDTO genericEstatusDTO);

}
