
package com.hospital.centro.service;

import com.hospital.centro.model.Enfermero;
import java.util.List;


public interface IEnfermeroService {
    
    //para traer todos
    public List<Enfermero>getEnfermeros();
    
    //para crear
    public void saveEnfermero(Enfermero enferme);
    
    //para eliminar
    public void deleteEnfermero(Long id_enferme);
    
    //para buscar o traer uno en cuestion por su id
    public Enfermero findEnfermero(Long id_enferme);
    
    //para editar forma facil
    public Enfermero edit(Enfermero enferme);
    
    //para cuando ya aiga acabado de atender a un paciente que el atribito 'disponible_ono' sea 'true' osea que este disponible
    public Enfermero disponible (Long id_enfe);
    
    
}
