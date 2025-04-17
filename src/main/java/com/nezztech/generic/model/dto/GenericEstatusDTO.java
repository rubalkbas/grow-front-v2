package com.nezztech.generic.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO
 * 
 * @author 
 * 
 */
@Getter
@Setter
public class GenericEstatusDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

	private String estatus;

}
