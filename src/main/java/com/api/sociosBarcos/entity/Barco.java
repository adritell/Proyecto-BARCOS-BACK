package com.api.sociosBarcos.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;




@Entity
@Data
public class Barco {

	/** Identificador único del barco. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;



	@Column(name = "numeroMatricula")
	@NotNull(message = "El número de matrícula no puede ser nulo")
	private Integer numeroMatricula;



	@Column(name = "numeroAmarre")
	@NotNull(message = "El número de amarre no puede ser nulo")
	private Integer numeroAmarre;



	@Column(name = "cuota")
	@NotNull(message = "La cuota no puede ser nula")
	private Double cuota;

	// Relacion con Socio, indica el dueño del barco. 
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "socio_id")
	private Socio socio;

	// Relación con Salida, indica la lista de salidas asociadas al barco.
	@OneToMany(mappedBy = "barco")
	@JsonBackReference
	private List<Salida> salidas;

	
	

}