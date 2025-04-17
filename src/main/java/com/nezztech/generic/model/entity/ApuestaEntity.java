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
@Table(name="apuesta_cliente", schema="internanueva")
@Getter
@Setter
public class ApuestaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_apuesta_cliente")
	private Long idApuestaCliente;	
	
	@Column(name = "tipo_compra")
	private String tipoCompra;
	
	@Column(name = "compra")
	private String compra;
	
	@Column(name = "valor_unidad")
	private Double valorUnidad;
	
	@Column(name = "unidades")
	private Double unidades;
	
	@Column(name = "monto_apuesta")
	private Double montoApuesta;
	
	@Column(name = "variacion")
	private Double variacion;
	
	@Column(name = "ganancia_perdida")
	private Double gananciaPerdida;
	
	@Column(name = "bloque_compra")
	private String bloqueCompra;
	
	@Column(name = "estatus_compra")
	private String estatusCompra;	
	
	@Column(name = "fecha_creacion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fecha_cierre")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fechaCierre;
	
	@Column(name = "id_usuario")
	private Long idUsuario;

}
