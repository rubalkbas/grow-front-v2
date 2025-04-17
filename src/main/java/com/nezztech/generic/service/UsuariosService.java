package com.nezztech.generic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.nezztech.generic.generico.service.GenericService;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.UsuariosEntity;
import com.nezztech.generic.repository.IUsuarioRepository;
import com.nezztech.generic.util.GenericasConstantes;

import lombok.extern.slf4j.Slf4j;

/**
 * IMPLEMENTACIÓN DE SERVICE
 * 
 * @author 
 */
@Slf4j
@Service
public class UsuariosService extends GenericService<UsuariosEntity, Long> implements IUsuariosService {
	
	private final IUsuarioRepository iUsuarioRepository; 

	public UsuariosService(IUsuarioRepository repository) { 
        super(repository);
        this.iUsuarioRepository = repository;
    }

	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	public ResponseDTO consultaUsuariosClientes( Long idAdmin ) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		List<UsuariosEntity> lista = new ArrayList<>();
		
		try {
			
			log.info("-");
			
			lista = iUsuarioRepository.findByIdAdmin(idAdmin);
			
			List<UsuariosEntity> lista2 = lista.stream()
                    .filter(usuario -> usuario.getRol().getIdRol() == 2)
                    .collect(Collectors.toList());
						
			if(lista2.isEmpty()) {
				
				responseDTO.setEstatus(GenericasConstantes.EMPTY);
				responseDTO.setMensaje(GenericasConstantes.NO_HAY_DATOS);
				
			} else {
				
				responseDTO.setLista(lista2);				
				responseDTO.setEstatus(GenericasConstantes.OK);
				responseDTO.setMensaje(GenericasConstantes.CONSULTA_EXITOSA);
				
			}

		}catch(DataAccessException e ) {
			
			log.info("-");
			log.info("ERROR : " + e.getMessage());
			
			responseDTO.setEstatus(GenericasConstantes.ERROR);
			responseDTO.setCodError(e.getMessage());
			responseDTO.setMensaje(GenericasConstantes.ERROR_INTERNO_DEL_SISTEMA);
			
		}
		
		log.info("-");
		
		return responseDTO;		
		
	}
}
