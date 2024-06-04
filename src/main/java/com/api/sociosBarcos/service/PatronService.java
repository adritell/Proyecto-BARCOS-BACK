package com.api.sociosBarcos.service;



import java.util.List;

import com.api.sociosBarcos.entity.Patron;




public interface PatronService {
	


	Patron agregarPatron(Patron patron);



    Patron obtenerPatronPorId(Long id);



    
    Patron actualizarPatron(Long id, Patron patron);



    void eliminarPatron(Long id);



    List<Patron> listarTodosLosPatrones();
}