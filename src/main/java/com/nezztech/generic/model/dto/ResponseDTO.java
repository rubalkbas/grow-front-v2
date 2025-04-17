package com.nezztech.generic.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ENTITY
 * 
 * @author 
 * 
 */
@Getter
@Setter
public class ResponseDTO<T> implements Serializable {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**ESTATUS*/
	protected String estatus;
	/**MENSAJE*/
	protected String mensaje;
	/**LISTA*/
	protected List<?> lista;
	/**DTO*/
	protected T dto;
	/**COD ERROR*/
	protected String codError;
	
	protected String sumaTotal;

}
