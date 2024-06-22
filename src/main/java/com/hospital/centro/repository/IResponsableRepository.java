
package com.hospital.centro.repository;

import com.hospital.centro.model.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResponsableRepository extends JpaRepository<Responsable, Long>{
    
}
