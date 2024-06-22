
package com.hospital.centro.service;

import com.hospital.centro.model.RegistroPacientes;
import java.time.LocalDate;
import java.util.List;


public interface IRegistroPacientesService {
    
    //para traer todos
    public List<RegistroPacientes> getRegisPacient();
    
    //para crear
    public void saveRegisPacient(RegistroPacientes regisPacient, Long id_respo);
    
    //el crear paar el editar
    public void saveRegisPacientEdit(RegistroPacientes regisPacient);
    
    //para eliminar
    public void deleteRegisPacient(Long num_regis);
    
    //para traer uno solo
    public RegistroPacientes findRegisPacient(Long num_regis);
    
    //para editar
    public void editRegis (RegistroPacientes regisPacient);
    
    //para cancelar el pago del paciente
    public void pagoListo(Long num_regis);
    
    //por si debe mas de un pago y quiere pagar todo debera pasar su nombre apellido y fecha nacimiento
    public void pagarTodo (String nombre, String apellido, LocalDate fecha_nac);
    
    //para mirar si tiene deudas en pacinete y si tiene mas de tres pagos pendientes no se lo atendera
    public List<RegistroPacientes> getPaciDuedas (String nombre, String apellido, LocalDate fecha_nac);
    
}
