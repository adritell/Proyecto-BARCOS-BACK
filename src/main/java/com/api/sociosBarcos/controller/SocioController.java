package com.api.sociosBarcos.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.sociosBarcos.dto.SocioDto;
import com.api.sociosBarcos.entity.Socio;
import com.api.sociosBarcos.service.SocioService;



@RestController
@RequestMapping("/api/v1/socios")
public class SocioController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SocioService socioService;

    

    @GetMapping
    public ResponseEntity<List<Socio>> listarTodasLasSocio() {
        List<Socio> socios = socioService.listarTodasLasSocio();
        return ResponseEntity.ok(socios);
    }
    
    
    @PostMapping
    public ResponseEntity<Socio> agregarSocio(@RequestBody SocioDto socioDto) {
        Socio socio = convertirADto(socioDto);
        Socio nuevoSocio = socioService.agregarSocio(socio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSocio);
    }
    
    
    /**
    Obtiene el socio con la mayor cantidad de barcos asociados
    */
   @GetMapping("/maxNumBarcos")
   public ResponseEntity<Socio> buscarSocioMaxBarcos() {
       Socio socio = socioService.buscarSocioMaxBarcos();
       return ResponseEntity.ok(socio);
   }
    
    
    /*Obtiene una lista de todos los socios sin ningún barco asociado*/
    @GetMapping("/sinBarco")
    public ResponseEntity<List<Socio>> getSociosSinBarco() {
        List<Socio> sociosSinBarco = socioService.findAllWithoutBarco();
        return ResponseEntity.ok().body(sociosSinBarco);
    }

    
    
    @GetMapping("/{id}")
    public ResponseEntity<Socio> obtenerSocioPorId(@PathVariable Long id) {
        Socio socio = socioService.obtenerSocioPorId(id);
        if (socio != null) {
            return ResponseEntity.ok(socio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizarSocio(@PathVariable Long id, @RequestBody SocioDto socioDto) {
        Socio socio = convertirADto(socioDto);
        Socio socioActualizado = socioService.actualizarSocio(id, socio);
        if (socioActualizado != null) {
            return ResponseEntity.ok(socioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSocio(@PathVariable Long id) {
        socioService.eliminarSocio(id);
        return ResponseEntity.ok("El socio con ID " + id + " ha sido eliminado correctamente.");
    }

    
    
    

    


    /**
      Convierte un objeto SocioDto a un objeto Socio.
     */
    private Socio convertirADto(SocioDto socioDto) {
        return modelMapper.map(socioDto, Socio.class);
    }

}