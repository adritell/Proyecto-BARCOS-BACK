package com.api.sociosBarcos.service;




import java.util.List;

import com.api.sociosBarcos.entity.Salida;





public interface SalidaService {



    Salida agregarSalida(Salida salida);



    Salida obtenerSalidaPorId(Long id);



    Salida actualizarSalida(Long id, Salida salida);



    void eliminarSalida(Long id);



    List<Salida> listarTodosLasSalidas();
}