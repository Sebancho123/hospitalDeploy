
package com.hospital.centro.controller;

import com.hospital.centro.model.Responsable;
import com.hospital.centro.service.IResponsableService;
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
public class ResponsableController {
    
    @Autowired
    private IResponsableService iRespServ;
    
    //para traer todos
    @GetMapping("responsable/traerlos")
    public List<Responsable>getResponsable() {
        return iRespServ.getResponsable();
    }
    
    //para crear
    @PostMapping("responsable/crear")
    public String saveResponsable(@RequestBody Responsable respo) {
        iRespServ.saveEditRespo(respo);
        return "se creo correctamente";
    }
    
    //para eliminar
    @DeleteMapping("responsable/eliminar/{id_respo}")
    public String deleteResponsable (@PathVariable Long id_respo) {
        iRespServ.deleteResponsable(id_respo);
        return "se elimino correctamente";
    }
    
    //para traer uno en cuestion 
    @GetMapping("responsable/buscar/{id_respo}")
    public Responsable findResponsable(@PathVariable Long id_respo) {
        return iRespServ.findResponsable(id_respo);
    }
    
    //para editar
    @PutMapping("responsable/editar")
    public Responsable editResponsable (@RequestBody Responsable respo) {
        iRespServ.editResponsable(respo);
        return iRespServ.findResponsable(respo.getId());
    } 
    
    //para buscar por el nombre a un responsable lo hago para ue el buscar sea mejor y mas facil que el de buscar por id 
    @GetMapping("responsable/buscar/{nombre}")
    public List<Responsable> buscarRespoForName(@PathVariable String nombre) {
        return iRespServ.buscarRespoForName(nombre);
    }
        
    
}
