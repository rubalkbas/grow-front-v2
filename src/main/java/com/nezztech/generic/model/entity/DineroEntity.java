package com.nezztech.generic.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name="dinero", schema="internanueva")
@Getter
@Setter
public class DineroEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dinero")
	private Long idDinero;

	@Column(name = "dinero")
	private Double dinero;
	
	@Column(name = "accion")
	private String accion;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "estatus_retiro")
	private Integer estatusRetiro;
	
	@Column(name = "fecha_creacion", insertable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "id_usuario")
	private Long idUsuario;

}
