package com.nezztech.generic.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.nezztech.generic.generico.service.GenericService;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ApalancamientoEntity;
import com.nezztech.generic.repository.IApalancamientoRepository;
import com.nezztech.generic.util.GenericasConstantes;

import lombok.extern.slf4j.Slf4j;

/**
 * IMPLEMENTACIÓN DE SERVICE
 * 
 * @author 
 */
@Slf4j
@Service
public class ApalancamientoService extends GenericService<ApalancamientoEntity, Long> implements IApalancamientoService {
	
	private final IApalancamientoRepository iApalancamientoRepository;
	
	public ApalancamientoService(IApalancamientoRepository repository) { 
        super(repository);
        this.iApalancamientoRepository = repository;
    }
	
	/**
	 * Consulta por ID.
	 * 
	 * @param activo
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO consultaPorIdCliente(Long id) {

		ResponseDTO responseDTO = new ResponseDTO();

		try {

			log.info("-");

			List<ApalancamientoEntity> lista = iApalancamientoRepository.findByIdUsuario(id);

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

}
