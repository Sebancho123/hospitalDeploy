
package com.hospital.centro.repository;

import com.hospital.centro.model.Secretario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISecretarioRepository extends JpaRepository<Secretario, Long>{
    
}
