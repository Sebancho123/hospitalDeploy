
package com.hospital.centro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Secretario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id_secre;
    private String nom_secre;
    private String apellido;
    private boolean disponible_ono;
    private int num_telefonico;

    public Secretario() {
    }

    public Secretario(Long id_secre, String nom_secre, String apellido, boolean disponible_ono, int num_telefonico) {
        this.id_secre = id_secre;
        this.nom_secre = nom_secre;
        this.apellido = apellido;
        this.disponible_ono = disponible_ono;
        this.num_telefonico = num_telefonico;
    }
     
}
