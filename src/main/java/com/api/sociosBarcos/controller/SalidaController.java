package com.api.sociosBarcos.controller;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.sociosBarcos.dto.SalidaDto;
import com.api.sociosBarcos.entity.Barco;
import com.api.sociosBarcos.entity.Patron;
import com.api.sociosBarcos.entity.Salida;
import com.api.sociosBarcos.exceptions.BarcoNotFoundException;
import com.api.sociosBarcos.exceptions.PatronNotFoundException;
import com.api.sociosBarcos.repository.BarcoRepository;
import com.api.sociosBarcos.repository.PatronRepository;
import com.api.sociosBarcos.repository.SalidaRepository;
import com.api.sociosBarcos.serviceImpl.BarcoServiceImpl;
import com.api.sociosBarcos.serviceImpl.PatronServiceImpl;
import com.api.sociosBarcos.serviceImpl.SalidaServiceImpl;




/**
 * Controlador REST que maneja las solicitudes relacionadas con la entidad Salida.
 */
@RestController
@RequestMapping("/api/v1/salida")
public class SalidaController {
    
    @Autowired
    private SalidaServiceImpl salidaService;
    
    @Autowired
    private PatronServiceImpl patronService;
    
    @Autowired
    private BarcoServiceImpl barcoService;
    
    @Autowired
    BarcoRepository barcoRepository;
    
    @Autowired
    PatronRepository patronRepository;
    
    @Autowired
    private SalidaRepository salidaRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    
    
    @GetMapping
    public ResponseEntity<List<Salida>> listarTodosLasSalidas() {
        List<Salida> salidas = salidaService.listarTodosLasSalidas();
        return ResponseEntity.ok(salidas);
    }
    
    
    


    @PostMapping
    public ResponseEntity<Salida> agregarSalida(@RequestBody SalidaDto salidaDto) {
    	try {
    		Salida salida = convertirADto(salidaDto);

            // Verificar si el barco existe
            Optional<Barco> barcoOptional = barcoRepository.findById(salidaDto.getBarco());
            if (barcoOptional.isEmpty()) {
                throw new BarcoNotFoundException("No se encontró un barco con el ID proporcionado: " + salidaDto.getBarco());
            }
            Barco barco = barcoOptional.get();

            // Verificar si el patrón existe
            Optional<Patron> patronOptional = patronRepository.findById(salidaDto.getPatron());
            if (patronOptional.isEmpty()) {
                throw new PatronNotFoundException("No se encontró un patrón con el ID proporcionado: " + salidaDto.getPatron());
            }
            Patron patron = patronOptional.get();

            // Asignar el barco y el patrón a la salida
            salida.setBarco(barco);
            salida.setPatron(patron);

            // Agregar la salida
            Salida nuevaSalida = salidaService.agregarSalida(salida);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSalida);		
		} catch (PatronNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		} catch (BarcoNotFoundException es) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
    	catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    	
    	
    }
    
    
    
    @GetMapping("/buscarPorDestino")
    public List<Salida> getSalidasPorDestino(@RequestParam String destino) {
        return salidaRepository.findByDestino(destino);
    }



    
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Salida> obtenerSalidaPorId(@PathVariable Long id) {
        Salida salida = salidaService.obtenerSalidaPorId(id);
        if (salida != null) {
            return ResponseEntity.ok(salida);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    
    
    @PutMapping("/{id}")
    public ResponseEntity<Salida> actualizarSalida(@PathVariable Long id, @RequestBody SalidaDto salidaDto) {
        Salida salida = convertirADto(salidaDto);
        Salida salidaActualizada = salidaService.actualizarSalida(id, salida);
        if (salidaActualizada != null) {
            return ResponseEntity.ok(salidaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSalida(@PathVariable Long id) {
        salidaService.eliminarSalida(id);
        return ResponseEntity.ok("La salida con ID " + id + " ha sido eliminada correctamente.");
    }

    
    
    /**
     * Convierte un objeto SalidaDto a un objeto Salida.
     */
    private Salida convertirADto(SalidaDto salidaDto) {
        Salida salida = modelMapper.map(salidaDto, Salida.class);
        
        // Convertir el ID del barco y del patrón a objetos Barco y Patron respectivamente
        Long barcoId = salidaDto.getBarco();
        Barco barco = barcoService.obtenerBarcoPorId(barcoId);
        
        Long patronId = salidaDto.getPatron();
        Patron patron = patronService.obtenerPatronPorId(patronId);
        
        salida.setBarco(barco);
        salida.setPatron(patron);
        
        return salida;
    }

}