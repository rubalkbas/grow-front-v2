package com.nezztech.generic.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nezztech.generic.model.entity.DineroEntity;

/**
 * 
 * INTERFACE REPOSITORY
 * 
 * @author 
 * 
 */
@Repository
public interface IDineroRepository extends JpaRepository<DineroEntity, Long> {

	List<DineroEntity> findByAccionAndIdUsuario( String accion, Long idDinero);
	
	List<DineroEntity> findByAccionAndIdUsuarioAndEstatusRetiro( String accion, Long idDinero, Integer estatusRetiro ); 
	
	List<DineroEntity> findByAccionAndEstatusRetiro( String accion, Integer estatusRetiro ); 
	
    @Query("SELECT SUM(p.dinero) FROM DineroEntity p WHERE p.accion = 'INGRESO' and p.idUsuario = :idUsuario")
	BigDecimal sumaIngresoIdUsuario(@Param("idUsuario") Long idUsuario); 
       
    @Query("SELECT SUM(p.dinero) FROM DineroEntity p WHERE p.accion = 'CREDITO' and p.idUsuario = :idUsuario")
	BigDecimal sumaCreditoIdUsuario(@Param("idUsuario") Long idUsuario);
    
    @Query("SELECT SUM(p.dinero) FROM DineroEntity p WHERE p.accion = 'RETIRO' and estatusRetiro = 1 and p.idUsuario = :idUsuario")
   	BigDecimal sumaIRetiroEfectuadoIdUsuario(@Param("idUsuario") Long idUsuario);
    
    @Query("SELECT SUM(p.dinero) FROM DineroEntity p WHERE p.accion = 'RETIRO' and estatusRetiro = 0 and p.idUsuario = :idUsuario")
   	BigDecimal sumaIRetiroSolcitadoIdUsuario(@Param("idUsuario") Long idUsuario);
    
}
