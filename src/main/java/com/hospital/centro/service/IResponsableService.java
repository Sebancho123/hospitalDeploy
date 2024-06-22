
package com.hospital.centro.service;

import com.hospital.centro.model.Responsable;
import java.util.List;


public interface IResponsableService {
    
    //para traer todos
    public List<Responsable>getResponsable();
    
    //para crear
    public void saveResponsable (Responsable respo);
    
    //para eliminar
    public void deleteResponsable(Long id_respo);
    
    //para traer uno en cuestion 
    public Responsable findResponsable(Long id_respo);
    
    //para editar 
    public void editResponsable(Responsable respo);
    
    //el save para el editar
    public void saveEditRespo (Responsable respo);
    
    //para buscar por el nombre a un responsable
    public List<Responsable> buscarRespoForName(String nombre);
    
    
}
