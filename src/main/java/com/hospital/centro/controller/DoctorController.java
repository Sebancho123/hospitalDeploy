
package com.hospital.centro.controller;

import com.hospital.centro.model.Doctor;
import com.hospital.centro.model.RegistroPacientes;
import com.hospital.centro.service.IDoctorService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    
    @Autowired
    private IDoctorService iDocSer;
    
    //para traer todos
    @GetMapping("/doctor/traerlos")
    public List<Doctor> getDoctors() {
        
        return iDocSer.getDoctors();
    }
    
    //para crear
    @PostMapping("doctor/crear")
    public String saveDoctor (@RequestBody Doctor doc) {
        
        iDocSer.saveDoctor(doc);
        return "se creo correctamente";
        
    }
    
    //para eliminar
    @DeleteMapping("doctor/eliminar/{id_doc}")
    public String deleteDoctor(@PathVariable Long id_doc) {
        
        iDocSer.deleteDoctor(id_doc);
        return "Se borro correctamente";
        
    }
    
    //para encontar un doctor especifico por su id
    @GetMapping("doctor/buscar/{id_doc}")
    public Doctor findDoctor (@PathVariable Long id_doc) {
   
        return iDocSer.findDoctor(id_doc);
        
    }
    
    //para editar with id
    @PutMapping("doctor/editarConId/{idOriginal}")
    public Doctor editWithId (@PathVariable Long idOriginal, @RequestParam(required = false, name = "id") Long idNueva, 
                              @RequestParam(required = false, name = "nombre") String nuevoNombre, 
                              @RequestParam(required = false, name = "apellido") String nuevoApellido, 
                              @RequestParam(required = false, name = "nivel") int nuevoNivel, 
                              @RequestParam(required = false, name = "disponible_ono") boolean disponible_ono) {
        
        
        iDocSer.editWithId(idOriginal, idNueva, nuevoNombre, nuevoApellido, nuevoNivel, disponible_ono);
        
        Doctor doct = iDocSer.findDoctor(idNueva);
        return doct;
    }
    
    //para editar objeto completo
    @PutMapping("doctor/editar")
    public Doctor editDoctor(@RequestBody Doctor doc) {
        return iDocSer.editDoctor(doc);
    }
    
    //para cuando ya aiga acaba de atender a un paciente que el atribito 'disponible_ono sea 'true' osea que este disponible'
    @PutMapping("doctor/{id_doc}/paciente/atendido")
    public Doctor disponible (@PathVariable Long id_doc) {
        return iDocSer.disponible(id_doc);
    }
    
    //para saber cuantos pacientes atendio en un determinado dia pasandole la fecha
    @GetMapping("doctor/atendidoshoy/{id_doc}/{fechaQAtndio}")
    public List<RegistroPacientes> getAtenEnUndia(@PathVariable Long id_doc, @PathVariable LocalDate fechaQAtndio) {
        
        return iDocSer.PaciatesEnUnDia(id_doc, fechaQAtndio);
    }
    
}
