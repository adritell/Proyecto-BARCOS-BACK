package com.api.sociosBarcos.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.sociosBarcos.entity.Barco;
import com.api.sociosBarcos.entity.Patron;
import com.api.sociosBarcos.entity.Salida;
import com.api.sociosBarcos.repository.BarcoRepository;
import com.api.sociosBarcos.repository.PatronRepository;
import com.api.sociosBarcos.repository.SalidaRepository;
import com.api.sociosBarcos.service.SalidaService;




@Service
public class SalidaServiceImpl implements SalidaService {

    @Autowired
    private SalidaRepository salidaRepository;

    @Autowired
    private BarcoRepository barcoRepository;

    @Autowired
    private PatronRepository patronRepository;



    @Override
    public Salida agregarSalida(Salida salida) {
        Long barcoId = salida.getBarco().getId();
        Long patronId = salida.getPatron().getId();

        // Verificar si el barco y el patrón asociados a la salida existen en la base de datos
        Optional<Barco> barcoOptional = barcoRepository.findById(barcoId);
        Optional<Patron> patronOptional = patronRepository.findById(patronId);

        if (barcoOptional.isPresent() && patronOptional.isPresent()) {
            return salidaRepository.save(salida);
        } else {
            throw new IllegalArgumentException("El barco o el patrón asociado a la salida no existen");
        }
    }



    @Override
    public Salida obtenerSalidaPorId(Long id) {
        Optional<Salida> salidaOptional = salidaRepository.findById(id);
        return salidaOptional.orElse(null);
    }



    
    @Override
    public Salida actualizarSalida(Long id, Salida nuevaSalida) {
        Optional<Salida> salidaExistenteOptional = salidaRepository.findById(id);
        if (salidaExistenteOptional.isPresent()) {
            Salida salidaExistente = salidaExistenteOptional.get();
            salidaExistente.setDestino(nuevaSalida.getDestino());
            salidaExistente.setFecha(nuevaSalida.getFecha());
            salidaExistente.setFecha_salida(nuevaSalida.getFecha_salida());

            return salidaRepository.save(salidaExistente);
        } else {
            return null;
        }
    }



    
    @Override
    public void eliminarSalida(Long id) {
        salidaRepository.deleteById(id);
    }



    
    @Override
    public List<Salida> listarTodosLasSalidas() {
        return salidaRepository.findAll();
    }
}