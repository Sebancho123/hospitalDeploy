package com.hospital.centro.service;

import com.hospital.centro.model.Doctor;
import com.hospital.centro.model.RegistroPacientes;
import com.hospital.centro.repository.IDoctorRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private IDoctorRepository iDocRepo;

    @Autowired
    private IRegistroPacientesService iRePaci;

    //para traer todos
    @Override
    public List<Doctor> getDoctors() {
        List<Doctor> listaDoctores = iDocRepo.findAll();

        return listaDoctores;

    }

    //para crear
    @Override
    public void saveDoctor(Doctor doc) {
        iDocRepo.save(doc);
    }

    //para eliminar
    @Override
    public void deleteDoctor(Long id_doc) {
        iDocRepo.deleteById(id_doc);
    }

    //para encontar un doctor especifico por su id
    @Override
    public Doctor findDoctor(Long id_doc) {
        return iDocRepo.findById(id_doc).orElse(null);
    }

    //para editar with id
    @Override
    public void editWithId(Long idOriginal, Long idNueva, String nuevoNombre, String nuevoApellido, int nuevoNivel, boolean disponible_ono) {

        Doctor doc = this.findDoctor(idOriginal);

        doc.setId(idNueva);
        doc.setNombre(nuevoNombre);
        doc.setApellido(nuevoApellido);
        doc.setNivel(nuevoNivel);
        doc.setDisponible_ono(disponible_ono);

        this.saveDoctor(doc);

    }

    //para editar objeto completo
    @Override
    public Doctor editDoctor(Doctor doc) {
        this.saveDoctor(doc);
        return doc;
    }

    //para cuando ya aiga acaba de atender a un paciente que el atribito 'disponible_ono sea 'true' osea que este disponible'
    @Override
    public Doctor disponible(Long id_doc) {

        List<RegistroPacientes> listaRegisPaci = iRePaci.getRegisPacient();

        Doctor doc = this.findDoctor(id_doc);
        doc.setDisponible_ono(true);
        this.editDoctor(doc);
        this.saveDoctor(doc);

        for (RegistroPacientes regisPaci : listaRegisPaci) {
            if (regisPaci.getUndoctor() != null) {
                if (doc.getId().equals(regisPaci.getUndoctor().getId())) {

                    String regisSiHayCreado = regisPaci.getRegistro();

                    regisPaci.setRegistro(regisSiHayCreado + "," + " " + " el doctor que lo atendio es : " + doc.getNombre() + " el id es: " +  doc.getId());
                    regisPaci.setUndoctor(null);
                    iRePaci.editRegis(regisPaci);
                }

            }
        }

        return doc;

    }

    //para saber cuantos pacientes atendio en un determinado dia pasandole la fecha
    @Override
    public List<RegistroPacientes> PaciatesEnUnDia(Long id_doc, LocalDate fechaQAtndio) {

        List<RegistroPacientes> listaRegisPacientes = iRePaci.getRegisPacient();
        List<RegistroPacientes> listaPacientes = new ArrayList<RegistroPacientes>();

        for (RegistroPacientes regisPaci : listaRegisPacientes) {
            Long id_regisDoc = regisPaci.getUndoctor().getId();
            if (id_regisDoc.equals(id_doc)) {
                if (regisPaci.getFecha_registro().equals(fechaQAtndio)) {
                    listaPacientes.add(regisPaci);
                }
            }
        }

        return listaPacientes;

    }

}
