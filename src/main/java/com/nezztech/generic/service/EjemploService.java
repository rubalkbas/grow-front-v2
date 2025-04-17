package com.nezztech.generic.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nezztech.generic.generico.service.GenericService;
import com.nezztech.generic.model.dto.GenericEstatusDTO;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.EjemploEntity;
import com.nezztech.generic.repository.IEjemploRepository;
import com.nezztech.generic.util.GenericasConstantes;

import lombok.extern.slf4j.Slf4j;

/**
 * IMPLEMENTACIÓN DE SERVICE
 * 
 * @author 
 */
@Slf4j
@Service
public class EjemploService extends GenericService<EjemploEntity, Long> implements IEjemploService { // extiende los metodos genericos
	
	private final IEjemploRepository ejemploRepository; // se pone cuando se pondran endpoints extras que se ocupen de los que no estan en los genericos

	public EjemploService(IEjemploRepository repository) { // se pone para tener endpoints genericos
        super(repository);
        this.ejemploRepository = repository;
    }
	
	/**
	 * Actualizar
	 * 
	 * @param entity
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO actualizarbyEstatus(GenericEstatusDTO ejemploDTO) {

		ResponseDTO response = new ResponseDTO();	

		try {
			
			Optional<EjemploEntity> entidad = ejemploRepository.findById(ejemploDTO.getId());

			EjemploEntity en = entidad.get();
			en.setEstatus(ejemploDTO.getEstatus());
			
			repository.save(en);

			response.setDto(en);
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
