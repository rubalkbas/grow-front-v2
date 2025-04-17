package com.nezztech.generic.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nezztech.generic.model.entity.HistoricoApuestaEntity;

/**
 * 
 * INTERFACE REPOSITORY
 * 
 * @author 
 * 
 */
@Repository
public interface IHistoricoRepository extends JpaRepository<HistoricoApuestaEntity, Long>{
	
	List<HistoricoApuestaEntity> findByIdApuestaCliente(  Long idApuestaCliente ); 

	@Query("SELECT SUM(p.montoGanPer) FROM HistoricoApuestaClienteEntity p WHERE  p.idApuestaCliente = :idApuestaCliente")
   	BigDecimal sumaGananciaPerdida(@Param("idApuestaCliente") Long idApuestaCliente);

}
