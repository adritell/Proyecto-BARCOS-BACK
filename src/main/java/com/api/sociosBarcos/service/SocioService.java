package com.api.sociosBarcos.service;



import java.util.List;

import com.api.sociosBarcos.entity.Socio;





public interface SocioService {



    Socio agregarSocio(Socio socio);



    Socio obtenerSocioPorId(Long id);



    Socio actualizarSocio(Long id, Socio socio);



    void eliminarSocio(Long id);



    List<Socio> listarTodasLasSocio();
    
    
    
    
    Socio buscarSocioMaxBarcos();
    
    
    List<Socio> findAllWithoutBarco ();
}