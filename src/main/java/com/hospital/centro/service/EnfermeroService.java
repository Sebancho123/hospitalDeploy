package com.hospital.centro.service;

import com.hospital.centro.model.Enfermero;
import com.hospital.centro.model.RegistroPacientes;
import com.hospital.centro.repository.IEnfermeroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnfermeroService implements IEnfermeroService {

    @Autowired
    private IEnfermeroRepository iEnfeRepo;

    //para llamar al metodo getAll de pacientes
    @Autowired
    private IRegistroPacientesService iRePaci;

    //para traer todos
    @Override
    public List<Enfermero> getEnfermeros() {

        List<Enfermero> listaEnfermeros = iEnfeRepo.findAll();
        return listaEnfermeros;

    }

    //para crear
    @Override
    public void saveEnfermero(Enfermero enferme) {
        iEnfeRepo.save(enferme);
    }

    //para eliminar
    @Override
    public void deleteEnfermero(Long id_enferme) {
        iEnfeRepo.deleteById(id_enferme);
    }

    //para buscar o traer uno en cuestion por su id
    @Override
    public Enfermero findEnfermero(Long id_enferme) {
        return iEnfeRepo.findById(id_enferme).orElse(null);
    }

    //para editar forma facil
    @Override
    public Enfermero edit(Enfermero enferme) {
        this.saveEnfermero(enferme);
        return enferme;
    }

    //para cuando ya aiga acabado de atender a un paciente que el atribito 'disponible_ono' sea 'true' osea que este disponible
    @Override
    public Enfermero disponible(Long id_enfe) {

        List<RegistroPacientes> listaRegisPaci = iRePaci.getRegisPacient();

        Enfermero enfer = this.findEnfermero(id_enfe);
        enfer.setDisponible_ono(true);
        this.edit(enfer);
        this.saveEnfermero(enfer);

        for (RegistroPacientes regispaci : listaRegisPaci) {
            if (regispaci.getEnferme() != null) {
                if (enfer.getId().equals(regispaci.getUndoctor().getId())) {

                    String regisSiHayCreado = regispaci.getRegistro();
                    regispaci.setEnferme(null);
                    regispaci.setRegistro(regisSiHayCreado + "," + " " + " el nombre del enfermero es : " + " " + enfer.getNombre() + " El id Es: " + enfer.getId());
                    iRePaci.editRegis(regispaci);
                    break;
                }
            }
        }

        return enfer;
    }

}
