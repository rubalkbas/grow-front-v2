package com.nezztech.generic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nezztech.generic.model.entity.EjemploEntity;

/**
 * 
 * INTERFACE REPOSITORY
 * 
 * @author 
 * 
 */
@Repository
public interface IEjemploRepository extends JpaRepository<EjemploEntity, Long>{

}
