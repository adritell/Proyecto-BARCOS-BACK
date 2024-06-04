package com.api.sociosBarcos.dto;


import java.sql.Date;

import lombok.Data;



@Data
public class SalidaDto {
	
    private String destino;
	
    private Date fecha;
	
    private Date fecha_salida;
	
    //ID del barco asociado a la salida. 
    private Long barco;
	
    // ID del patrón asociado a la salida. 
    private Long patron;


}