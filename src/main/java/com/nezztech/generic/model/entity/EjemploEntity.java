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
@Table(name="ejemplo", schema="ittiva")
@Getter
@Setter
public class EjemploEntity implements Serializable {

    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ejemplo")
	private Long idEjemplo;
	
	@Column(name = "nombre_ejemplo")
	private String nombreEjemplo;
	
	@Column(name = "estatus")
	private String estatus;
	
}
