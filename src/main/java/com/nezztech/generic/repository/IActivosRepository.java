package com.nezztech.generic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nezztech.generic.model.entity.ActivosEntity;

import jakarta.transaction.Transactional;

/**
 * 
 * INTERFACE REPOSITORY
 * 
 * @author 
 * 
 */
@Repository
public interface IActivosRepository extends JpaRepository<ActivosEntity, Long> {
	
	List<ActivosEntity> findByActivo( String activo );
	
	@Modifying
    @Transactional
    @Query("UPDATE ActivosEntity a SET a.estatus = :estatus WHERE a.idActivo = :idActivo")
    int actualizarEstatus(@Param("idActivo") Long idActivo, @Param("estatus") String estatus);

}
