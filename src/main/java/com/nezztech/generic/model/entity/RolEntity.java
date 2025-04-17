package com.nezztech.generic.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * ENTITY
 * 
 * @author 
 * 
 */
@Entity
@Table(name="roles", schema="internanueva")
@Getter
@Setter
public class RolEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol")
	private Long idRol;
	
	@Column(name = "nombre_rol")
	private String nombreRol;
	
	@Column(name = "descripcion")
	private String descripcion;

}
