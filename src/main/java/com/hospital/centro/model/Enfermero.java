
package com.hospital.centro.model;

import jakarta.persistence.Entity;

@Entity
public class Enfermero extends Persona{

    public Enfermero() {
    }

    public Enfermero(Long id, String nombre, String apellido, int nivel, int cobra, boolean disponible_ono) {
        super(id, nombre, apellido, nivel, cobra, disponible_ono);
    }
    
    
    
}
