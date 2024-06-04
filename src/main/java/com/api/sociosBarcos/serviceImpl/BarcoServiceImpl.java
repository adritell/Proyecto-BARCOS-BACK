package com.api.sociosBarcos.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.sociosBarcos.entity.Barco;
import com.api.sociosBarcos.entity.Socio;
import com.api.sociosBarcos.repository.BarcoRepository;
import com.api.sociosBarcos.repository.SocioRepository;
import com.api.sociosBarcos.service.BarcoService;




@Service
public class BarcoServiceImpl implements BarcoService {

    @Autowired
    private BarcoRepository barcoRepository;
    
    @Autowired
    private SocioRepository socioRepository;

    
    @Override
    public Barco agregarBarco(Barco barco) {
            return barcoRepository.save(barco);
       
    }

    
    @Override
    public Barco obtenerBarcoPorId(Long id) {
        return barcoRepository.findById(id).orElse(null);
    }



    
    @Override
    public Barco actualizarBarco(Long id, Barco barco) {
        Optional<Barco> barcoExistenteOptional = barcoRepository.findById(id);
        if (barcoExistenteOptional.isPresent()) {
            Barco barcoExistente = barcoExistenteOptional.get();
            Socio socio = barco.getSocio();
            Optional<Socio> socioOptional = socioRepository.findById(socio.getId());
            if (socioOptional.isPresent()) {
                barcoExistente.setNumeroMatricula(barco.getNumeroMatricula());
                barcoExistente.setNumeroAmarre(barco.getNumeroAmarre());
                barcoExistente.setCuota(barco.getCuota());
                barcoExistente.setSocio(socio);
                return barcoRepository.save(barcoExistente);
            } else {
                throw new RuntimeException("El socio con ID " + socio.getId() + " no existe");
            }
        } else {
            throw new RuntimeException("El barco con ID " + id + " no existe");
        }
    }



    @Override
    public void eliminarBarco(Long id) {
        barcoRepository.deleteById(id);
    }



    
    @Override
    public List<Barco> listarTodosLosBarco() {
        return barcoRepository.findAll();
    }

}