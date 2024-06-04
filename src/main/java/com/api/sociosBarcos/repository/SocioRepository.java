package com.api.sociosBarcos.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.sociosBarcos.entity.Socio;




@Repository 
public interface SocioRepository extends JpaRepository<Socio, Long> {
	
	
	//Consulta personalizada que busca los socios que no tengan un barco asociado
	 @Query("SELECT s FROM Socio s WHERE s.barcos IS EMPTY")
	    List<Socio> findAllWithoutBarco();
	
	
	//Consulta para obtener el socio con la mayor cantidad de barcos asociados
    @Query("SELECT s FROM Socio s WHERE SIZE(s.barcos) = (SELECT MAX(SIZE(s1.barcos)) FROM Socio s1)")
    Socio findSocioWithMostBarcos();

}
