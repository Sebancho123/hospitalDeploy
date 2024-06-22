package com.hospital.centro.service;

import com.hospital.centro.model.RegistroPacientes;
import com.hospital.centro.model.Secretario;
import com.hospital.centro.repository.ISecretarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretarioService implements ISecretarioService {

    @Autowired
    private ISecretarioRepository iSecreRepo;

    @Autowired
    private IRegistroPacientesService iRePaci;

    //para traerlos todos
    @Override
    public List<Secretario> getSecretarios() {
        List<Secretario> listaSecres = iSecreRepo.findAll();
        return listaSecres;
    }

    //para crear
    @Override
    public void saveSecretario(Secretario secre) {
        iSecreRepo.save(secre);
    }

    //para eliminar 
    @Override
    public void deleteSecretario(Long id_secre) {
        iSecreRepo.deleteById(id_secre);
    }

    //para traer uno en especifico
    @Override
    public Secretario findSecretario(Long id_secre) {
        return iSecreRepo.findById(id_secre).orElse(null);
    }

    //para editar
    @Override
    public void editSecre(Secretario secre) {
        this.saveSecretario(secre);
    }

    //para cuando ya aiga acabado de atender a un paciente que el atribito 'disponible_ono' sea 'true' osea que este disponible
    @Override
    public Secretario disponible(Long id_secre) {

        List<RegistroPacientes> listaRegisPaci = iRePaci.getRegisPacient();

        Secretario secre = this.findSecretario(id_secre);
        secre.setDisponible_ono(true);
        this.editSecre(secre);
        this.saveSecretario(secre);

        
        for (RegistroPacientes regisPaci : listaRegisPaci) {
            if (regisPaci.getSecre() != null) {
                if (secre.getId_secre().equals(regisPaci.getSecre().getId_secre())) {

                    String regisSiHayCreado = regisPaci.getRegistro();

                    regisPaci.setRegistro(regisSiHayCreado + "," + " " + " el nombre del secretarioe es : " + " " + secre.getNom_secre() + " El id Es : " + secre.getId_secre());
                    regisPaci.setSecre(null);
                    iRePaci.editRegis(regisPaci);
                    break;
                }
            }
        }

        return secre;
    }

    //para llamar al hospital y mandar ambulancias corespondientes a la cantidad de heridos
    @Override
    public String mandarAmbu(int numeroHos, String lugarCalle, int cantPerHeridas) {

        List<Secretario> listaSecres = this.getSecretarios();

        String mensaje = "";

        for (Secretario secre : listaSecres) {
            if (secre.isDisponible_ono() == true) {

                if (secre.getNum_telefonico() == numeroHos) {

                    switch (cantPerHeridas) {
                        case 1, 2:
                            mensaje = "acabamos de enviar la ambulancia pequeña";
                            break;
                        case 3, 4, 5:
                            mensaje = "acabamos de enviar la ambulancia mediana";
                            break;
                        case 6:
                            mensaje = "acabamos de enviar la ambulancia grande";
                            break;

                    }
                    break;

                } else {
                    mensaje = "numero equivocado";
                }

            } else {
                mensaje = "no hay secretarios disponibles";
            }

        }

        return mensaje;

    }

}
