package com.api.sociosBarcos.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.sociosBarcos.entity.Patron;




@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

}