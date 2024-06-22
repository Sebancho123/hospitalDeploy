
package com.hospital.centro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class RegistroPacientes {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long num_registro;
    private String nombre_paci;
    private String apellido_paci;
    
    @Temporal(TemporalType.DATE)
    private LocalDate fechaNac;
    
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_registro;
    private int nivel_gravedad;
    private String pago;
    private int totalApagar;
    private String registro;
    
    @OneToOne
    private Doctor Undoctor;
    
    @OneToOne
    private Enfermero enferme;
    
    @OneToOne
    private Secretario secre;
    
    @OneToOne
    private Responsable respo;

    public RegistroPacientes() {
    }

    public RegistroPacientes(Long num_registro, String nombre_paci, String apellido_paci, LocalDate fechaNac, LocalDate fecha_registro, int nivel_gravedad, String pago, int totalApagar, String registro, Doctor Undoctor, Enfermero enferme, Secretario secre, Responsable respo) {
        this.num_registro = num_registro;
        this.nombre_paci = nombre_paci;
        this.apellido_paci = apellido_paci;
        this.fechaNac = fechaNac;
        this.fecha_registro = fecha_registro;
        this.nivel_gravedad = nivel_gravedad;
        this.pago = pago;
        this.totalApagar = totalApagar;
        this.registro = registro;
        this.Undoctor = Undoctor;
        this.enferme = enferme;
        this.secre = secre;
        this.respo = respo;
    }
    
}
