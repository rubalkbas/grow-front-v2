package com.nezztech.generic.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.nezztech.generic.generico.service.GenericService;
import com.nezztech.generic.model.dto.ResponseDTO;
import com.nezztech.generic.model.entity.ApalancamientoEntity;
import com.nezztech.generic.model.entity.ApuestaEntity;
import com.nezztech.generic.model.entity.HistoricoApuestaEntity;
import com.nezztech.generic.model.entity.UsuariosEntity;
import com.nezztech.generic.repository.IApalancamientoRepository;
import com.nezztech.generic.repository.IApuestaRepository;
import com.nezztech.generic.repository.IHistoricoRepository;
import com.nezztech.generic.repository.IUsuarioRepository;
import com.nezztech.generic.util.GenericasConstantes;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * IMPLEMENTACIÓN DE SERVICE
 * 
 * @author 
 */
@Slf4j
@Service
public class ApuestaService implements IApuestaService{

	
	@Autowired
	IApalancamientoRepository iApalancamientoRepository;
	
	@Autowired
	IUsuarioRepository iUsuarioRepository;
	
	@Autowired
	IHistoricoRepository iHistoricoRepository;
	
	@Autowired
	IApuestaRepository iApuestaRepository;
	

	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@Transactional
	@Override
	public ResponseDTO consultaApuestasAbiertasClienteID( ApuestaEntity apuestaClienteEntity ) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		List<ApuestaEntity> lista = new ArrayList<>();
		
		try {			
						
			log.info("-");
						
			lista = iApuestaRepository.findByestatusCompraAndIdUsuarioOrderByFechaCreacionDesc( "ABIERTA" , apuestaClienteEntity.getIdUsuario() );
			
			if(lista.isEmpty()) {
				
				responseDTO.setEstatus(GenericasConstantes.EMPTY);
				responseDTO.setMensaje(GenericasConstantes.NO_HAY_DATOS);
				
			} else {
				
				responseDTO.setLista(lista);
				
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
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@Transactional
	@Override
	public ResponseDTO consultaApuestasCerradasClienteID( ApuestaEntity apuestaClienteEntity ) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		List<ApuestaEntity> lista = new ArrayList<>();
		
		try {
			
			log.info("-");
						
			lista = iApuestaRepository.findByestatusCompraAndIdUsuarioOrderByFechaCierreDesc( "CERRADA" , apuestaClienteEntity.getIdUsuario() );
			
			if(lista.isEmpty()) {
				
				responseDTO.setEstatus(GenericasConstantes.EMPTY);
				responseDTO.setMensaje(GenericasConstantes.NO_HAY_DATOS);
				
			} else {
				
				responseDTO.setLista(lista);
				
				BigDecimal total = iApuestaRepository.sumaGananciasPerdidasApuestas(apuestaClienteEntity.getIdUsuario());
				
				responseDTO.setSumaTotal(String.valueOf(total));
				
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
	
	
	@Transactional
	@Override
	public ResponseDTO crearApuestas( ApuestaEntity apuesta ) {
		
		ResponseDTO responseDTO = new ResponseDTO();
	
				
		try {

			log.info("-");
			
			apuesta.setEstatusCompra("ABIERTA");
			//apuesta.setFechaCreacion(new Date());
			apuesta.setGananciaPerdida(0.0);
			apuesta.setFechaCierre(null);
			
			
			//obtiee datos del usuario 
			Optional<UsuariosEntity> usuarioRecuperado = iUsuarioRepository.findById(apuesta.getIdUsuario());
			UsuariosEntity usuario = usuarioRecuperado.get();
			
			Double balance = usuario.getTotalDinero();
			Double margenLibre = usuario.getMargenLibre();
			Double margen = usuario.getMargen();
			
			Double porcentaje = 0.15;
			
			Double treintaPorciento = balance * porcentaje;
			
			Double sobrante = balance-apuesta.getMontoApuesta();
			
			if(sobrante < treintaPorciento) { 
				
				responseDTO.setEstatus("FALTA");
				responseDTO.setMensaje(GenericasConstantes.CONSULTA_EXITOSA);
				
			}else {
				
				//guarda apuesta
				ApuestaEntity apuestaAbierta = iApuestaRepository.save(apuesta);			
				
				
				margenLibre = margenLibre - apuestaAbierta.getMontoApuesta();			
				margen = margen + apuestaAbierta.getMontoApuesta();

				log.info("margenLibre inicio: " + margenLibre);
				log.info("margen inicio: " + margen);
				
				usuario.setMargenLibre(margenLibre);
				usuario.setMargen(margen);
				
				//actualiza margen libre y margen
				UsuariosEntity user =iUsuarioRepository.save(usuario);
				
				log.info("margenLibre final: " + user.getMargenLibre());
				log.info("margen final: " + user.getMargen());
				
				//enviar correo
//				String cuerpo = "Se abrio una apuesta";
//				String info = "Aposto a la "+apuestaAbierta.getTipoCompra()+" en " +apuestaAbierta.getBloqueCompra() + " a : " + apuestaAbierta.getCompra() +
//						      " , unidades : " +apuestaAbierta.getUnidades() +". Monto de $ " + apuestaAbierta.getMontoApuesta(); 
				
				HistoricoApuestaEntity historicoApuestaClienteEntity = new HistoricoApuestaEntity();
				historicoApuestaClienteEntity.setIdApuestaCliente(apuestaAbierta.getIdApuestaCliente());
				historicoApuestaClienteEntity.setMontoGanPer(0.0);
				historicoApuestaClienteEntity.setGanPer("PIERDE");
				historicoApuestaClienteEntity.setValorCompra(apuestaAbierta.getValorUnidad());
				historicoApuestaClienteEntity.setValorWebSocket(apuestaAbierta.getValorUnidad());
				
				iHistoricoRepository.save(historicoApuestaClienteEntity);
				
				startConnection(apuestaAbierta);
				
				//notificacionCorreoService.envioCorreo(apuestaAbierta.getIdUsuario(), cuerpo, info);
				
				responseDTO.setDto(apuestaAbierta);
				responseDTO.setEstatus(GenericasConstantes.OK);
				responseDTO.setMensaje(GenericasConstantes.ALTA_EXITOSA);

				
			}
			

			log.info("FINALIZA ");

		}catch(DataAccessException  e){//| MessagingException | IOException e ) {
			
			log.info("-");
			log.info("ERROR : " + e.getMessage());
			
			responseDTO.setEstatus(GenericasConstantes.ERROR);
			responseDTO.setCodError(e.getMessage());
			responseDTO.setMensaje(GenericasConstantes.ERROR_INTERNO_DEL_SISTEMA);
			
		}
		
		log.info("-");
		
		
		
		
		return responseDTO;		
		
	}
	
	@Transactional
	@Override
	public ResponseDTO cerrarApuesta( ApuestaEntity apuestaClienteEntity ) {
		
		ResponseDTO responseDTO = new ResponseDTO();
				
		try {
			
			log.info("-");			
			
			apuestaClienteEntity.setEstatusCompra("CERRADA");
			//apuestaClienteEntity.setFechaCierre(new Date());
			
			ApuestaEntity cierre = iApuestaRepository.save(apuestaClienteEntity);
			
			actualizaMontos(cierre.getGananciaPerdida(), cierre);
			
			//String cuerpo = "Se cerro una apuesta";
			//String info = "La ganancia o perdida fue de : " + gan.toString(); 
			
			//notificacionCorreoService.envioCorreo(cierre.getIdUsuario(), cuerpo, info);
			
			responseDTO.setEstatus(GenericasConstantes.OK);
			responseDTO.setMensaje("POSICION CERRADA CORRECTAMENTE");
		
			log.info("FINALIZA ");

		}catch(DataAccessException e ) { //| MessagingException | IOException e ) {
			
			log.info("-");
			log.info("ERROR : " + e.getMessage());
			
			responseDTO.setEstatus(GenericasConstantes.ERROR);
			responseDTO.setCodError(e.getMessage());
			responseDTO.setMensaje(GenericasConstantes.ERROR_INTERNO_DEL_SISTEMA);
			
		}
		
		log.info("-");
		
		return responseDTO;		
		
	}
	
	@Transactional
	private void actualizaMontos(Double gan,ApuestaEntity apuestaClienteEntity) {
		
		//actualizar balance, margen libre y margen
		Optional<UsuariosEntity> usuarioRecuperado = iUsuarioRepository.findById(apuestaClienteEntity.getIdUsuario());
		UsuariosEntity usuarioEnvio = usuarioRecuperado.get();
		
		Double balance = usuarioEnvio.getTotalDinero();
		Double marLib = usuarioEnvio.getMargenLibre();
		Double mar = usuarioEnvio.getMargen();
		
		//ctualizamargen
		Double newMar = mar - apuestaClienteEntity.getMontoApuesta();
		
		//actualiza margen libre
		Double newmarLib = marLib + apuestaClienteEntity.getMontoApuesta() + gan;			
		
		//actualiza balance
		Double newbalance = balance + gan ;
			
		//se actualiza balance
		usuarioEnvio.setTotalDinero(newbalance);
		//se actuliza margen libre
		usuarioEnvio.setMargenLibre(newmarLib);			
		//se actuliza margen
		usuarioEnvio.setMargen(newMar);
		
		UsuariosEntity actualizado = iUsuarioRepository.save(usuarioEnvio);
				
	}
	
	
	@Autowired
    private RestTemplate restTemplate;
	
	public String startConnection(ApuestaEntity apuestaClienteEntity) {
		
        //String url = "http://localhost:8093/websocket/start" ;
        String url = "https://app.growingcapitalmaker.org:8093/websocket/start" ;
        
        log.info("url : " +url);
        
        return restTemplate.postForObject(url,apuestaClienteEntity, String.class);
    }
	
	
	public String cerrarConexionWebsocket(Integer idApuestaCliente) {
		
        //String url = "http://localhost:8093/websocket/stopClient?clientId="+idApuestaCliente;
        String url = "https://app.growingcapitalmaker.org:8093/websocket/stopClient?clientId="+idApuestaCliente;
        
        log.info("url : " +url);
        
        return restTemplate.getForObject(url, String.class);
    }
	
	
	
	/**
	 * 
	 * @author NEZZTECH
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@Transactional
	@Override
	public ResponseDTO consultaApuestasHistoricoAbiertasClienteID( HistoricoApuestaEntity historicoApuestaClienteEntity ) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		List<HistoricoApuestaEntity> lista = new ArrayList<>();
		
		try {			
						
			log.info("-");
						
			lista = iHistoricoRepository.findByIdApuestaCliente(  historicoApuestaClienteEntity.getIdApuestaCliente() );
			
			if(lista.isEmpty()) {
				
				responseDTO.setEstatus(GenericasConstantes.EMPTY);
				responseDTO.setMensaje(GenericasConstantes.NO_HAY_DATOS);
				
			} else {
				
				responseDTO.setLista(lista);
				
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
	
	
	 @Transactional
		@Override
		public ResponseDTO actualizarApuesta( ApuestaEntity apuestaClienteEntity ) {
			
			ResponseDTO responseDTO = new ResponseDTO();
					
			try {
				
				log.info("-");			
							
				iApuestaRepository.save(apuestaClienteEntity);
	
				log.info("FINALIZA ");
				
				responseDTO.setEstatus(GenericasConstantes.OK);
				responseDTO.setMensaje(GenericasConstantes.ACTUALIZACION_EXITOSA);

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
	 
	 
	 
	 /**
		 * 
		 * @author NEZZTECH
		 * 
		 * @param 
		 * @return 
		 * 
		 */
		
		@Override
		public ResponseDTO consultaApalancamientoClienteID( ApalancamientoEntity apalancamiento ) {
			
			ResponseDTO responseDTO = new ResponseDTO();
			
			List<ApalancamientoEntity> lista = new ArrayList<>();
			
			try {
				
				log.info("-");
							
				lista = iApalancamientoRepository.findByIdUsuario(apalancamiento.getIdUsuario());
				
				if(lista.isEmpty()) {
					
					responseDTO.setEstatus(GenericasConstantes.EMPTY);
					responseDTO.setMensaje(GenericasConstantes.NO_HAY_DATOS);
					
				} else {
					
					responseDTO.setLista(lista);
					
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
		
		
		
		@Transactional
		@Override
		public ResponseDTO actualizarApalancamiento( ApalancamientoEntity apalancamiento ) {
			
			ResponseDTO responseDTO = new ResponseDTO();
					
			try {
				
				log.info("-");			
							
				iApalancamientoRepository.save(apalancamiento);
	
				log.info("FINALIZA ");
				
				responseDTO.setEstatus(GenericasConstantes.OK);
				responseDTO.setMensaje(GenericasConstantes.ACTUALIZACION_EXITOSA);

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
