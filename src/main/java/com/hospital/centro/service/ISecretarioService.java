
package com.hospital.centro.service;

import com.hospital.centro.model.Secretario;
import java.util.List;


public interface ISecretarioService {
    
    //para traerlos todos
    public List<Secretario> getSecretarios();
    
    //para crear
    public void saveSecretario(Secretario secre);
    
    //para eliminar 
    public void deleteSecretario(Long id_secre);
    
    //para traer uno en especifico
    public Secretario findSecretario(Long id_secre);
    
    //para editar
    public void editSecre(Secretario secre);
    
    //para cuando ya aiga acabado de atender a un paciente que el atribito 'disponible_ono' sea 'true' osea que este disponible
    public Secretario disponible (Long id_secre);
    
    //para llamar al hospital y mandar ambulancias corespondientes a la cantidad de heridos
    public String mandarAmbu(int numeroHos, String lugarCalle, int cantPerHeridas);
    
}
