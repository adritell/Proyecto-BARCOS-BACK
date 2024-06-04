package com.api.sociosBarcos.entity;



import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;




@Entity
@Data
public class Salida {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "destino")
    @NotBlank(message = "El destino no puede estar en blanco")
    @NotNull
    private String destino;


    @Column(name = "fecha")
    @NotNull(message = "La fecha no puede ser nula")
    @Temporal(TemporalType.DATE)
    private Date fecha;


    @Column(name = "fecha_salida")
    @NotNull(message = "La fecha de salida no puede ser nula")
    private Date fecha_salida;


    @ManyToOne
    @JoinColumn(name = "barco_id")
    @JsonManagedReference
    private Barco barco;


    @ManyToOne
    @JoinColumn(name = "patron_id")
    @JsonManagedReference
    private Patron patron;
    
    
    
	
}