package com.nezztech.generic.service;

import com.nezztech.generic.generico.service.IGenericService;
import com.nezztech.generic.model.dto.GenericEstatusDTO;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.EjemploEntity;

/**
 * INTERFACE DE SERVICE
 * 
 * @author 
 */
public interface IEjemploService extends IGenericService<EjemploEntity, Long>{
	
	/**
	 * Actualizar
	 * 
	 * @return ResponseDTO
	 */
	ResponseDTO actualizarbyEstatus(GenericEstatusDTO ejemploDTO);

}
