package com.api.sociosBarcos.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Entity
@Data
public class Patron {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "nombre")
	@NotBlank(message = "El nombre no puede estar en blanco")
	private String nombre;




	@OneToOne(optional = true)
	private Socio socio;

	
	//Relación con Salida, indica la lista de salidas asociadas al patrón.
	@OneToMany(mappedBy = "patron")
	@JsonBackReference
	private List<Salida> salidas;

	
	
	

}