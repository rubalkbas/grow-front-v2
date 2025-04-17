package com.nezztech.generic.generico.service;

import com.nezztech.generic.model.dto.ResponseDTO;

/**
 * INTERFACE DE SERVICE
 * 
 * @author ITTIVA
 */
public interface IGenericService<T, Long> {

	/**
	 * Consultar todos
	 * 
	 * @return ResponseDTO
	 */
	public ResponseDTO consultaTodos();

	/**
	 * Consulta por ID.
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO consultaPorId(Long id);

	/**
	 * Delete por ID.
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	public ResponseDTO deleteById(Long id);

	/**
	 * Alta
	 * 
	 * @param entity
	 * @return ResponseDTO
	 */
	public ResponseDTO guardar(T entity);

	/**
	 * Actualiza
	 * 
	 * @param entity
	 * @return ResponseDTO
	 */
	public ResponseDTO actualizar(T entity);

}
