
package com.hospital.centro.controller;

import com.hospital.centro.model.Secretario;
import com.hospital.centro.service.ISecretarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretarioController {
    
    @Autowired
    private ISecretarioService iSecreSer;
    
    //para traerlos
    @GetMapping("secretario/traerlos")
    public List<Secretario>getSecretarios() {
        return iSecreSer.getSecretarios();
    }
    
    //para crear
    @PostMapping("secretario/crear")
    public String saveSecre(@RequestBody Secretario secre) {
        iSecreSer.saveSecretario(secre);
        return "se creo correctamente";
    }
    
    //para eliminar 
    @DeleteMapping("secretario/eliminar/{id_secre}")
    public String deleteSecre (@PathVariable Long id_secre) {
        iSecreSer.deleteSecretario(id_secre);
        return "Se Elimino Correctamente";
    }
    
    //para traer uno en especifico
    @GetMapping("secretario/buscar/{id_secre}")
    public Secretario findSecre (@PathVariable Long id_secre) {
        return iSecreSer.findSecretario(id_secre);
    }
    
    //para editar
    @PutMapping("secretario/editar")
    public Secretario editSecre (@RequestBody Secretario secre) {
        iSecreSer.editSecre(secre);
        return iSecreSer.findSecretario(secre.getId_secre());
    }
    
    //para cuando ya aiga acabado de atender a un paciente que el atribito 'disponible_ono' sea 'true' osea que este disponible
    @PutMapping("secretario/{id_secre}/paciente/atendido")
    public Secretario disponible (@PathVariable Long id_secre) {
        return iSecreSer.disponible(id_secre);
    }
    
    //para llamar al hospital y mandar ambulancias corespondientes a la cantidad de heridos
    @GetMapping("secretario/{numeroHos}/{lugarCalle}/{cantPerHeridas}")
    public String mandarAmbu (@PathVariable int numeroHos, @PathVariable String lugarCalle, @PathVariable int cantPerHeridas) {
            
        return iSecreSer.mandarAmbu(numeroHos, lugarCalle, cantPerHeridas);
        
    }
    
}
