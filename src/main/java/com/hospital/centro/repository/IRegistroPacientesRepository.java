
package com.hospital.centro.repository;

import com.hospital.centro.model.RegistroPacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegistroPacientesRepository extends JpaRepository<RegistroPacientes, Long>{
    
}
