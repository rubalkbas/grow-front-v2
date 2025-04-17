package com.nezztech.generic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.nezztech.generic.generico.service.GenericService;
import com.nezztech.generic.model.dto.GenericEstatusDTO;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ActivosEntity;
import com.nezztech.generic.model.entity.EjemploEntity;
import com.nezztech.generic.repository.IActivosRepository;
import com.nezztech.generic.util.GenericasConstantes;

import lombok.extern.slf4j.Slf4j;

/**
 * IMPLEMENTACIÓN DE SERVICE
 * 
 * @author 
 */
@Slf4j
@Service
public class ActivosService extends GenericService<ActivosEntity, Long> implements IActivosService {
	
	private final IActivosRepository iActivosRepository;
	
	public ActivosService(IActivosRepository repository) {
        super(repository);
        this.iActivosRepository = repository;
    } 

	/**
	 * Consulta por ID.
	 * 
	 * @param activo
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO consultaPorActivo(String activo) {

		ResponseDTO responseDTO = new ResponseDTO();

		try {

			log.info("-");

			List<ActivosEntity> lista = iActivosRepository.findByActivo(activo);

			if (!lista.isEmpty()) {

				responseDTO.setLista(lista);
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
	 * Actualizar
	 * 
	 * @param entity
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO actualizarbyEstatus(GenericEstatusDTO genericEstatusDTO) {

		ResponseDTO response = new ResponseDTO();	

		try {
	
			iActivosRepository.actualizarEstatus(genericEstatusDTO.getId(),genericEstatusDTO.getEstatus());

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
