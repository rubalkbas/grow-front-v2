package com.nezztech.generic.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nezztech.generic.model.entity.ApuestaEntity;

/**
 * 
 * INTERFACE REPOSITORY
 * 
 * @author 
 * 
 */
@Repository
public interface IApuestaRepository extends JpaRepository<ApuestaEntity, Long> {
	
	List<ApuestaEntity> findByestatusCompraAndIdUsuarioOrderByFechaCreacionDesc( String estatusCompra, Long idUsuario ); 
	
	List<ApuestaEntity> findByestatusCompraAndIdUsuarioOrderByFechaCierreDesc( String estatusCompra, Long idUsuario ); 
	
	@Query("SELECT SUM(p.gananciaPerdida) FROM ApuestaClienteEntity p WHERE p.idUsuario = :idUsuario")
	BigDecimal sumaGananciasPerdidasApuestas(@Param("idUsuario") Long idUsuario);

}
