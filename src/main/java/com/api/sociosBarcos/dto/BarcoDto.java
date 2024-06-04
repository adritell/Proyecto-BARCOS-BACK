package com.api.sociosBarcos.dto;

import lombok.Data;


@Data
public class BarcoDto {
	
    private String numeroMatricula;
	
    private Integer numeroAmarre;
	
    private Double cuota;
	
    //ID del socio al que está asociado el barco.
    private Long socio_id;

	
	
	

}