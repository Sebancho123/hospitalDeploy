
package com.hospital.centro.service;

import com.hospital.centro.model.Doctor;
import com.hospital.centro.model.RegistroPacientes;
import java.time.LocalDate;
import java.util.List;


public interface IDoctorService{
    
    //para traer todos
    public List<Doctor> getDoctors();
    
    //para crear
    public void saveDoctor(Doctor doc);
    
    //para eliminar
    public void deleteDoctor(Long id_doc);
    
    //para encontar un doctor especifico por su id
    public Doctor findDoctor(Long id_doc);
    
    //para editar with id
    public void editWithId(Long idOriginal, Long idNueva, String nuevoNombre, String nuevoApellido, int nuevoNivel, boolean disponible_ono);
    
    //para editar objeto completo
    public Doctor editDoctor(Doctor doc);
    
    //para cuando ya aiga acaba de atender a un paciente que el atribito 'disponible_ono sea 'true' osea que este disponible'
    public Doctor disponible(Long id_doc);
    
    //para saber cuantos pacientes atendio en un determinado dia pasandole la fecha
    public List<RegistroPacientes> PaciatesEnUnDia(Long id_doc, LocalDate fechaQAtndio);
}
