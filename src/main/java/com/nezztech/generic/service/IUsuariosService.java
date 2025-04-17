package com.nezztech.generic.service;

import com.nezztech.generic.generico.service.IGenericService;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.UsuariosEntity;

/**
 * INTERFACE DE SERVICE
 * 
 * @author 
 */
public interface IUsuariosService extends IGenericService<UsuariosEntity, Long> {
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO consultaUsuariosClientes( Long idAdmin );

}
