
package com.hospital.centro.repository;

import com.hospital.centro.model.Enfermero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnfermeroRepository extends JpaRepository<Enfermero, Long>{
    
}
