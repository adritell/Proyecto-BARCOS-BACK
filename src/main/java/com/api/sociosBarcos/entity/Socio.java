package com.api.sociosBarcos.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Entity
@Data
public class Socio {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "nombre")
	@NotBlank(message = "El nombre no puede estar en blanco")
	private String nombre;


	@Column(name = "apellidos")
	@NotBlank(message = "Los apellidos no pueden estar en blanco")
	private String apellidos;


	@Column(name = "dni")
	@NotBlank(message = "El DNI no puede estar en blanco")
	private String dni;


	@OneToMany(mappedBy = "socio")
	@JsonBackReference
	private List<Barco> barcos;

	

}