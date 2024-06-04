package com.api.sociosBarcos.dto;

import lombok.Data;


@Data
public class PatronDto {

	private String nombre;

	private String apellido;

	//ID del socio asociado al patrón.
	private Long socio;
}