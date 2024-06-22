
package com.hospital.centro.controller;

import com.hospital.centro.model.RegistroPacientes;
import com.hospital.centro.service.IRegistroPacientesService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistroPacientesController {
    
    @Autowired
    private IRegistroPacientesService iRegisSer;
    
    //para traer todos
    @GetMapping("registros/traerlos")
    public List<RegistroPacientes>getRegisPacient() {
        return iRegisSer.getRegisPacient();
    }
    
    //para crear
    //el id por defecto para el responsable que es mayor siempre es 1 para q en el service lo tome como null y ya para el menor
    //el id del responsable que acabemos de crear
    @PostMapping("registros/crear/{id_respo}")
    public String saveRegisPacient(@RequestBody  RegistroPacientes regisPacient, @PathVariable Long id_respo) {
        iRegisSer.saveRegisPacient(regisPacient, id_respo);
                
        return "se creo correctamente";
    }
    
    //para eliminar
    @DeleteMapping("registros/eliminar/{num_regis}")
    public String deleteRegisPacient(@PathVariable Long num_regis) {
        iRegisSer.deleteRegisPacient(num_regis);
        return "Se Elimino Correctamente";
    }
    
    //para traer uno solo
    @GetMapping("registros/buscar/{num_regis}")
    public RegistroPacientes findRegisPacient (@PathVariable Long num_regis) {
        return iRegisSer.findRegisPacient(num_regis);
    }
    
    //para editar
    @PutMapping("registros/editar")
    public RegistroPacientes editRegis(@RequestBody RegistroPacientes regisPacient) {
        iRegisSer.editRegis(regisPacient);
        return iRegisSer.findRegisPacient(regisPacient.getNum_registro());
    }
    
    //para cancelar el pago del paciente
    @GetMapping("registros/pagar/{num_regis}")
    public String pagoList(@PathVariable Long num_regis){
        
        iRegisSer.pagoListo(num_regis);
        return "pago exitoso";
    }
    
    //por si debe mas de un pago y quiere pagar todo debera pasar su nombre apellido y fecha nacimiento
    @GetMapping("registros/pagartodo/{nombre}/{apellido}/{fecha_nac}")
    public String pagarTodo (@PathVariable String nombre, @PathVariable String apellido, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate fecha_nac) {
        iRegisSer.pagarTodo(nombre, apellido, fecha_nac);
        return "se pago todo correctamente";
    }
    
    //para mirar si tiene deudas en pacinete y si tiene mas de tres pagos pendientes no se lo atendera
    @GetMapping("registros/deudas/{nombre}/{apellido}/{fecha_nac}")
    public List<RegistroPacientes> getPaciDuedas (@PathVariable String nombre, @PathVariable String apellido, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate fecha_nac) {
        
        return iRegisSer.getPaciDuedas(nombre, apellido, fecha_nac);
    }
    
    
}
