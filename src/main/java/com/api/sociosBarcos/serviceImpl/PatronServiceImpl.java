package com.api.sociosBarcos.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.sociosBarcos.entity.Patron;
import com.api.sociosBarcos.repository.PatronRepository;
import com.api.sociosBarcos.service.PatronService;




@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;



    @Override
    public Patron agregarPatron(Patron patron) {
        return patronRepository.save(patron);
    }



    @Override
    public Patron obtenerPatronPorId(Long id) {
        return patronRepository.findById(id).orElse(null);
    }



    @Override
    public Patron actualizarPatron(Long id, Patron patron) {
        Patron nuevoPatron = obtenerPatronPorId(id);
        if (nuevoPatron != null) {
            nuevoPatron.setNombre(patron.getNombre());
            return patronRepository.save(nuevoPatron);
        } else {
            return null;
        }
    }



    @Override
    public void eliminarPatron(Long id) {
        patronRepository.deleteById(id);
    }



    @Override
    public List<Patron> listarTodosLosPatrones() {
        return patronRepository.findAll();
    }
}