package com.nezztech.generic.generico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.util.GenericasConstantes;

import lombok.extern.slf4j.Slf4j;

/**
 * IMPLEMENTACIÓN DE SERVICE
 * 
 * @author ITTIVA
 */
@Slf4j
@Service
public abstract class GenericService<T, Long> implements IGenericService<T, Long> {

	protected final JpaRepository<T, Long> repository;

	public GenericService(JpaRepository<T, Long> repository) {
		this.repository = repository;
	}

	/**
	 * Consulta todos
	 * 
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO consultaTodos() {

		ResponseDTO responseDTO = new ResponseDTO();

		try {

			log.info("-");

			List<T> lista = repository.findAll();

			if (lista.isEmpty()) {

				responseDTO.setEstatus(GenericasConstantes.EMPTY);
				responseDTO.setMensaje(GenericasConstantes.NO_HAY_DATOS);

			} else {

				responseDTO.setLista(lista);
				responseDTO.setEstatus(GenericasConstantes.OK);
				responseDTO.setMensaje(GenericasConstantes.CONSULTA_EXITOSA);

			}
		} catch (DataAccessException e) {

			log.info("-");
			log.error("ERROR: ", e);

			responseDTO.setEstatus(GenericasConstantes.ERROR);
			responseDTO.setMensaje(GenericasConstantes.ERROR_INTERNO_DEL_SISTEMA);
			responseDTO.setCodError(e.getMessage());

		}

		return responseDTO;

	}

	/**
	 * Consulta por ID.
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO consultaPorId(Long id) {

		ResponseDTO responseDTO = new ResponseDTO();

		try {

			log.info("-");

			Optional<T> empresaOpt = repository.findById(id);

			if (empresaOpt.isPresent()) {

				responseDTO.setDto(empresaOpt.get());
				responseDTO.setEstatus(GenericasConstantes.OK);
				responseDTO.setMensaje(GenericasConstantes.CONSULTA_EXITOSA);

			} else {

				responseDTO.setEstatus(GenericasConstantes.EMPTY);
				responseDTO.setMensaje(GenericasConstantes.NO_HAY_DATOS);

			}

		} catch (DataAccessException e) {

			log.info("-");
			log.error("ERROR: ", e);

			responseDTO.setEstatus(GenericasConstantes.ERROR);
			responseDTO.setMensaje(GenericasConstantes.ERROR_INTERNO_DEL_SISTEMA);
			responseDTO.setCodError(e.getMessage());

		}

		return responseDTO;

	}

	/**
	 * Delete por ID.
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO deleteById(Long id) {

		ResponseDTO response = new ResponseDTO();

		try {

			repository.deleteById(id);

			response.setEstatus(GenericasConstantes.OK);
			response.setMensaje(GenericasConstantes.BORRADO_EXITOSO);

		} catch (Exception e) {
			
			log.info("-");
			log.error("ERROR: ", e);

			response.setEstatus(GenericasConstantes.ERROR);
			response.setMensaje(GenericasConstantes.ERROR_INTERNO_DEL_SISTEMA);
			response.setCodError(e.getMessage());
		}

		return response;

	}

	/**
	 * Alta
	 * 
	 * @param entity
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO guardar(T entity) {

		ResponseDTO response = new ResponseDTO();

		try {

			entity = repository.save(entity);

			response.setDto(entity);
			response.setEstatus(GenericasConstantes.OK);
			response.setMensaje(GenericasConstantes.ALTA_EXITOSA);

		} catch (Exception e) {
			
			log.info("-");
			log.error("ERROR: ", e);
			
			response.setEstatus(GenericasConstantes.ERROR);
			response.setMensaje(GenericasConstantes.ERROR_INTERNO_DEL_SISTEMA);
			response.setCodError(e.getMessage());
		}
		return response;
	}

	/**
	 * Actualizar
	 * 
	 * @param entity
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO actualizar(T entity) {

		ResponseDTO response = new ResponseDTO();	

		try {

			repository.save(entity);

			response.setDto(entity);
			response.setEstatus(GenericasConstantes.OK);
			response.setMensaje(GenericasConstantes.ACTUALIZACION_EXITOSA);

		} catch (Exception e) {
			
			log.info("-");
			log.error("ERROR: ", e);
			
			response.setEstatus(GenericasConstantes.ERROR);
			response.setMensaje(GenericasConstantes.ERROR_INTERNO_DEL_SISTEMA);
			response.setCodError(e.getMessage());
		}
		return response;
	}

}
