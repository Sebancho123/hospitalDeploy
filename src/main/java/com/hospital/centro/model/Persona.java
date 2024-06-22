
package com.hospital.centro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Persona implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    private int nivel;
    private int cobra;
    private boolean disponible_ono;

    public Persona() {
    }

    public Persona(Long id, String nombre, String apellido, int nivel, int cobra, boolean disponible_ono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivel = nivel;
        this.cobra = cobra;
        this.disponible_ono = disponible_ono;
    }
}
