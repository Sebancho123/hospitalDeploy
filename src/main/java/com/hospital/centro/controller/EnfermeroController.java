
package com.hospital.centro.controller;

import com.hospital.centro.model.Enfermero;
import com.hospital.centro.service.IEnfermeroService;
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
public class EnfermeroController {
    
    @Autowired
    private IEnfermeroService iEnSer;
    
    //para traerlos todos
    @GetMapping("enfermero/traerlos")
    public List<Enfermero>getEnfermeros() {
        return iEnSer.getEnfermeros();
    }
    
    //para crear
    @PostMapping("enfermero/crear")
    public String savaEnfermero(@RequestBody Enfermero enfermero) {
        iEnSer.saveEnfermero(enfermero);
        return "Se creo correctamente";
    }
    
    //para eliminar
    @DeleteMapping("enfermero/eliminar/{id_enferme}")
    public String deleteEnferme (@PathVariable Long id_enferme) {
        iEnSer.deleteEnfermero(id_enferme);
        return "Se elimino correctamente";
    }
    
    //para buscar o traer uno en cuestion por su id
    @GetMapping("enfermero/buscar/{id_enferme}")
    public Enfermero findEnfermero (@PathVariable Long id_enferme) {
        
        return iEnSer.findEnfermero(id_enferme);
    }
    
    //para editar forma facil
    @PutMapping("enfermero/editar")
    public Enfermero edit(@RequestBody Enfermero enfermero) {
        
        return iEnSer.edit(enfermero);
    }
    
    //para cuando ya aiga acabado de atender a un paciente que el atribito 'disponible_ono' sea 'true' osea que este disponible
    @PutMapping("enfermero/{id_enfe}/paciente/atendido")
    public Enfermero disponible(@PathVariable Long id_enfe) {
        return iEnSer.disponible(id_enfe);
    }
    
}
