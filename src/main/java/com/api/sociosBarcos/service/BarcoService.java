package com.api.sociosBarcos.service;



import java.util.List;

import com.api.sociosBarcos.entity.Barco;





public interface BarcoService {
	
    
	Barco agregarBarco(Barco barco);

    
    Barco obtenerBarcoPorId(Long id);



    
    Barco actualizarBarco(Long id, Barco barco);



    void eliminarBarco(Long id);



    List<Barco> listarTodosLosBarco();
}