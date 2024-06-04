package com.api.sociosBarcos.controller;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.sociosBarcos.dto.BarcoDto;
import com.api.sociosBarcos.entity.Barco;
import com.api.sociosBarcos.entity.Socio;
import com.api.sociosBarcos.exceptions.SocioNotFoundException;
import com.api.sociosBarcos.serviceImpl.BarcoServiceImpl;
import com.api.sociosBarcos.serviceImpl.SocioServiceImpl;


@RestController
@RequestMapping("/api/v1/barcos")
public class BarcoController {
	
    @Autowired
    private SocioServiceImpl socioService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BarcoServiceImpl barcoService;

    
    
    
    @GetMapping
    public ResponseEntity<List<Barco>> listarTodosLosBarcos() {
        List<Barco> barcos = barcoService.listarTodosLosBarco();
        return ResponseEntity.ok(barcos);
    }
    
    


    
    @PostMapping
    public ResponseEntity<Barco> agregarBarco(@RequestBody BarcoDto barcoDto) {
        try {
            // Convertir el DTO a entidad Barco
            Barco barco = convertirADto(barcoDto);
            
            // Verificar si se proporcionó un socio_id
            Long socioId = barcoDto.getSocio_id();
            if (socioId != null) {
                // Verificar si el socio existe
                Socio socio = socioService.obtenerSocioPorId(socioId);
                if (socio == null) {
                    throw new SocioNotFoundException("El socio con ID " + socioId + " no existe.");
                }
                // Asignar el socio al barco
                barco.setSocio(socio);
            } else {
                // Si no se proporcionó un socio_id, establecerlo como null en el barco
                barco.setSocio(null);
            }
            
            // Agregar el barco
            Barco nuevoBarco = barcoService.agregarBarco(barco);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBarco);
        } catch (SocioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (NullPointerException ex) {
            // Manejar cualquier NullPointerException aquí
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }





   

    




    
    
    @GetMapping("/{id}")
    public ResponseEntity<Barco> obtenerBarcoPorId(@PathVariable Long id) {
        Barco barco = barcoService.obtenerBarcoPorId(id);
        if (barco != null) {
            return ResponseEntity.ok(barco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    
    
    @PutMapping("/{id}")
    public ResponseEntity<Barco> actualizarBarco(@PathVariable Long id, @RequestBody BarcoDto barcoDto) {
        Barco barco = convertirADto(barcoDto);
        Barco barcoActualizado = barcoService.actualizarBarco(id, barco);
        if (barcoActualizado != null) {
            return ResponseEntity.ok(barcoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarBarco(@PathVariable Long id) {
        barcoService.eliminarBarco(id);
        return ResponseEntity.ok("El barco con ID " + id + " ha sido eliminado correctamente.");
    }

    

    /**
     * Convierte un objeto BarcoDto a un objeto Barco.
     */
    private Barco convertirADto(BarcoDto barcoDto) {
        Barco barco = modelMapper.map(barcoDto, Barco.class); // Mapear el objeto BarcoDto a Barco
        
        Long socioId = barcoDto.getSocio_id();   
        if (socioId == null) {
            barco.setSocio(null); // Establecer el Socio como null en el Barco

        } else {
            Socio socio = socioService.obtenerSocioPorId(socioId);
            barco.setSocio(socio); // Asignar el Socio al Barco
        }
        
        return barco;
    }
}