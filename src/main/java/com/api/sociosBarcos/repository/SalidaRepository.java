package com.api.sociosBarcos.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.sociosBarcos.entity.Salida;




@Repository
public interface SalidaRepository extends JpaRepository<Salida, Long> {
	
	 // Consulta personalizada que busca salidas por destino
    @Query("SELECT s FROM Salida s WHERE s.destino = :destino")
    List<Salida> findByDestino(@Param("destino") String destino);
}

