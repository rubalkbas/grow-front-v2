package com.nezztech.generic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nezztech.generic.model.entity.ApalancamientoEntity;

/**
 * 
 * INTERFACE REPOSITORY
 * 
 * @author 
 * 
 */
@Repository
public interface IApalancamientoRepository extends JpaRepository<ApalancamientoEntity, Long> {
	
	List<ApalancamientoEntity> findByIdUsuario( Long id );

}
