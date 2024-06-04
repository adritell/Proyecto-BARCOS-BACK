package com.api.sociosBarcos.controller;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.sociosBarcos.dto.PatronDto;
import com.api.sociosBarcos.entity.Patron;
import com.api.sociosBarcos.entity.Socio;
import com.api.sociosBarcos.exceptions.SocioNotFoundException;
import com.api.sociosBarcos.serviceImpl.PatronServiceImpl;
import com.api.sociosBarcos.serviceImpl.SocioServiceImpl;





@RestController
@RequestMapping("/api/v1/patron")
public class PatronController {

    @Autowired
    private SocioServiceImpl socioService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatronServiceImpl patronService;

    
    

    @GetMapping
    public ResponseEntity<List<Patron>> listarTodosLosPatrones() {
        List<Patron> patrones = patronService.listarTodosLosPatrones();
        return ResponseEntity.ok(patrones);
    }
    
    
    


    
    @PostMapping
    public ResponseEntity<Patron> agregarPatron(@RequestBody PatronDto patronDto) {
        try {
            Patron patron = convertirADto(patronDto);
            
            // Verificar si se proporcionó un id de socio en el cuerpo de la solicitud
            Long socioId = patronDto.getSocio();
            if (socioId != null) {
                // Verificar si el socio existe
                Socio socio = socioService.obtenerSocioPorId(socioId);
                if (socio == null) {
                    throw new SocioNotFoundException("No se encontró ningún socio con el ID proporcionado: " + socioId);
                }
                // Asignar el socio al patrón
                patron.setSocio(socio);
            } else {
                // Si no se proporcionó un id de socio, establecerlo como null en el patrón
                patron.setSocio(null);
            }
            
            // Agregar el patrón
            Patron nuevoPatron = patronService.agregarPatron(patron);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPatron);
        } catch (SocioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Patron> obtenerPatronPorId(@PathVariable Long id) {
        Patron patron = patronService.obtenerPatronPorId(id);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Patron> actualizarPatron(@PathVariable Long id, @RequestBody PatronDto patronDto) {
        Patron patron = convertirADto(patronDto);
        Patron patronActualizado = patronService.actualizarPatron(id, patron);
        if (patronActualizado != null) {
            return ResponseEntity.ok(patronActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPatron(@PathVariable Long id) {
        patronService.eliminarPatron(id);
        return ResponseEntity.ok("El patrón con ID " + id + " ha sido eliminado correctamente.");
    }

    
    
    /**
     * Convierte un objeto PatronDto a un objeto Patron.
     */
    private Patron convertirADto(PatronDto patronDto) {
        Patron patron = modelMapper.map(patronDto, Patron.class);
        
        // Obtener el ID del socio del DTO
        Long socioId = patronDto.getSocio();
        
        if (socioId == null) {
            patron.setSocio(null); // Establecer el Socio como null en el Patrón
        } else {
            // Obtener el socio correspondiente al ID
            Socio socio = socioService.obtenerSocioPorId(socioId);
            
            if (socio == null) {
                // Manejar la situación donde no se encuentra el socio
                throw new SocioNotFoundException("No se encontró ningún socio con el ID proporcionado: " + socioId);
            }
            
            patron.setSocio(socio); // Asignar el Socio al Patrón
        }

        return patron;
    }
}