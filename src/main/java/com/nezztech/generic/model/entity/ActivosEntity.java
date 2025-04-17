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
@Table(name="activos", schema="internanueva")
@Getter
@Setter
public class ActivosEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_activo")
	private Long idActivo;

	@Column(name = "abreviacion")
	private String abreviacion;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "estatus", insertable = false, updatable = false)
	private String estatus;
	
	@Column(name = "activo")
	private String activo;

}
