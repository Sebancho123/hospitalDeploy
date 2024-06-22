
package com.hospital.centro.model;


import jakarta.persistence.Entity;

@Entity
public class Doctor extends Persona{

    public Doctor() {
    }

    public Doctor(Long id, String nombre, String apellido, int nivel, int cobra, boolean disponible_ono) {
        super(id, nombre, apellido, nivel, cobra, disponible_ono);
    }
    
}
