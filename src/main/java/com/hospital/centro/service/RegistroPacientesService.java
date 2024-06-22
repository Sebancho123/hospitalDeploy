package com.hospital.centro.service;

import com.hospital.centro.model.Doctor;
import com.hospital.centro.model.Enfermero;
import com.hospital.centro.model.RegistroPacientes;
import com.hospital.centro.model.Responsable;
import com.hospital.centro.model.Secretario;
import com.hospital.centro.repository.IRegistroPacientesRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroPacientesService implements IRegistroPacientesService {

    @Autowired
    private IRegistroPacientesRepository iRegiRepo;

    //lo usamos para usar el metodo que trae todos los secretarios
    @Autowired
    private ISecretarioService iSecreSer;

    //lo usamos para usar el metodo que trae todos los doctores
    @Autowired
    private IDoctorService iDocSer;

    //lo usamos para usar el metodo que trae todos los enfermeros
    @Autowired
    private IEnfermeroService iEnfSer;

    //lo usamos para usar el metodo que encuentra a un responsable en cuestion por su id para q si es menor de edad lo setee
    @Autowired
    private IResponsableService iResponSer;

    //para traer todos
    @Override
    public List<RegistroPacientes> getRegisPacient() {

        List<RegistroPacientes> listaRegistros = iRegiRepo.findAll();
        return listaRegistros;

    }

    //para crear y seetear cosas automaticas sin uno mismo teenr que ponerlas 
    @Override
    public void saveRegisPacient(RegistroPacientes regisPacient, Long id_respo) {

        List<Secretario> listaSecretarios = iSecreSer.getSecretarios();
        List<Doctor> listaDoctores = iDocSer.getDoctors();
        List<Enfermero> listaEnfermeros = iEnfSer.getEnfermeros();

        for (Secretario secre : listaSecretarios) {
            if (secre.isDisponible_ono() == true) {
                regisPacient.setSecre(secre);
                secre.setDisponible_ono(false);
                iSecreSer.editSecre(secre);
                break;
            } else {

            }
        }

        for (Doctor doc : listaDoctores) {
            if (doc.isDisponible_ono() == true) {
                if (regisPacient.getNivel_gravedad() == doc.getNivel()) {
                    regisPacient.setTotalApagar(doc.getCobra());
                    regisPacient.setUndoctor(doc);
                    regisPacient.setTotalApagar(doc.getCobra());
                    doc.setDisponible_ono(false);
                    /*LocalDate fechahoy = regisPacient.getFecha_registro();
                                    doc.setFecha_qatendio(fechahoy);*/
                    iDocSer.editDoctor(doc);

                    LocalDate fechaHoy = LocalDate.now();
                    LocalDate fechaNac = regisPacient.getFechaNac();
                    Period cant_anios = Period.between(fechaNac, fechaHoy);

                    Responsable respo = iResponSer.findResponsable(id_respo);

                    if (cant_anios.getYears() < 18) {
                        regisPacient.setRespo(respo);
                        iRegiRepo.save(regisPacient);
                    } else {
                        iRegiRepo.save(regisPacient);
                    }

                    break;
                }

            } else {
            }
        }

        for (Enfermero enfermero : listaEnfermeros) {
            if (enfermero.isDisponible_ono() == true) {
                if (regisPacient.getNivel_gravedad() == enfermero.getNivel()) {
                    regisPacient.setTotalApagar(enfermero.getCobra());
                    regisPacient.setEnferme(enfermero);
                    regisPacient.setTotalApagar(enfermero.getCobra());
                    enfermero.setDisponible_ono(false);
                    /*LocalDate fechahoy = regisPacient.getFecha_registro();
                    enfermero.setFecha_atender(fechahoy);*/
                    iEnfSer.edit(enfermero);

                    LocalDate fechaHoy = LocalDate.now();
                    LocalDate fechaNac = regisPacient.getFechaNac();
                    Period cant_anios = Period.between(fechaNac, fechaHoy);

                    Responsable respo = iResponSer.findResponsable(id_respo);

                    if (cant_anios.getYears() < 18) {
                        regisPacient.setRespo(respo);
                        iRegiRepo.save(regisPacient);
                    } else {
                        iRegiRepo.save(regisPacient);
                    }

                    break;
                }

            } else {
            }

        }

    }

    //para eliminar
    @Override
    public void deleteRegisPacient(Long num_regis) {
        iRegiRepo.deleteById(num_regis);
    }

    //para traer uno solo
    @Override
    public RegistroPacientes findRegisPacient(Long num_regis) {
        return iRegiRepo.findById(num_regis).orElse(null);
    }

    //para editar
    @Override
    public void editRegis(RegistroPacientes regisPacient) {
        this.saveRegisPacientEdit(regisPacient);
    }

    //el crear paar el editar este metodo no esta en el controller
    @Override
    public void saveRegisPacientEdit(RegistroPacientes regisPacient) {
        iRegiRepo.save(regisPacient);
    }

    //para cancelar el pago del paciente
    @Override
    public void pagoListo(Long num_regis) {

        RegistroPacientes registPac = this.findRegisPacient(num_regis);
        registPac.setPago("completado");

        this.editRegis(registPac);

    }

    //por si debe mas de un pago y quiere pagar todo debera pasar su nombre apellido y fecha nacimiento
    @Override
    public void pagarTodo(String nombre, String apellido, LocalDate fecha_nac) {

        List<RegistroPacientes> listapacis = this.getRegisPacient();

        for (RegistroPacientes registPac : listapacis) {
            if (registPac.getNombre_paci().equals(nombre)) {
                if (registPac.getApellido_paci().equals(apellido)) {
                    if (registPac.getFechaNac().equals(fecha_nac)) {

                        registPac.setPago("Completado");
                        registPac.setTotalApagar(0);

                        this.editRegis(registPac);

                    }
                }
            }
        }

    }

    //para mirar si tiene deudas en pacinete y si tiene mas de tres pagos pendientes no se lo atendera
    @Override
    public List<RegistroPacientes> getPaciDuedas(String nombre, String apellido, LocalDate fecha_nac) {

        List<RegistroPacientes> listaregisPacient = this.getRegisPacient();
        List<RegistroPacientes> listaRegPaci = new ArrayList<RegistroPacientes>();

        for (RegistroPacientes regisPaci : listaregisPacient) {
            if (regisPaci.getNombre_paci().equalsIgnoreCase(nombre)) {
                if (regisPaci.getApellido_paci().equalsIgnoreCase(apellido)) {
                    if (regisPaci.getFechaNac().equals(fecha_nac)) {
                        if (regisPaci.getPago().equals("pendiente")) {
                            listaRegPaci.add(regisPaci);
                        }
                    }
                }
            }
        }

        return listaRegPaci;

    }
}
