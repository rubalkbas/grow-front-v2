package com.nezztech.generic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nezztech.generic.model.entity.UsuariosEntity;

/**
 * 
 * INTERFACE REPOSITORY
 * 
 * @author 
 * 
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<UsuariosEntity, Long> {
	
	List<UsuariosEntity> findByIdAdmin( Long idAdmin );
	
	boolean existsByCorreo(String email);
	 
	Optional<UsuariosEntity> findByCorreo(String email);	

	
}
