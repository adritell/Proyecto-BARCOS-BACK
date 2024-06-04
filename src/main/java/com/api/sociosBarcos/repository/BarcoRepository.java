package com.api.sociosBarcos.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.sociosBarcos.entity.Barco;



@Repository
public interface BarcoRepository extends JpaRepository<Barco, Long> {
}