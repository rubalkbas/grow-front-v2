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
@Table(name="historico_apuesta_cliente", schema="internanueva")
@Getter
@Setter
public class HistoricoApuestaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_hist_apuest_cli")
	private Long idHistApuestaCliente;	
	
	@Column(name = "id_apuesta_cliente")
	private Long idApuestaCliente;
	
	@Column(name = "valor_compra")
	private Double valorCompra;
	
	@Column(name = "valor_web_socket")
	private Double valorWebSocket;
	
	@Column(name = "gan_per")
	private String ganPer;	

	@Column(name = "monto_gan_per")
	private Double montoGanPer;

}
