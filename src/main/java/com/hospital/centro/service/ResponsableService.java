
package com.hospital.centro.service;

import com.hospital.centro.model.Responsable;
import com.hospital.centro.repository.IResponsableRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponsableService implements IResponsableService{
    
    @Autowired
    private IResponsableRepository iResRepo;

    //para traer todos
    @Override
    public List<Responsable> getResponsable() {
        
        List<Responsable> listaResponsables = iResRepo.findAll();
        return listaResponsables;
        
    }

    //para crear
    @Override
    public void saveResponsable(Responsable respo) {
        iResRepo.save(respo);
    }

    //para eliminar
    @Override
    public void deleteResponsable(Long id_respo) {
        iResRepo.deleteById(id_respo);
    }

    //para traer uno en cuestion 
    @Override
    public Responsable findResponsable(Long id_respo) {
        return iResRepo.findById(id_respo).orElse(null);
    }

    //para editar
    @Override
    public void editResponsable(Responsable respo) {
        this.saveEditRespo(respo);
    }

    //el save para el editar
    @Override
    public void saveEditRespo(Responsable respo) {
        iResRepo.save(respo);
    }

    //para buscar por el nombre a un responsable
    @Override
    public List<Responsable> buscarRespoForName(String nombre) {
        
        List<Responsable> listaResponsables = this.getResponsable();
        List<Responsable> listaResponsa = new ArrayList<Responsable>();
        
        for (Responsable respo : listaResponsables) {
            if (respo.getNombre().equalsIgnoreCase(nombre)) {
                listaResponsa.add(respo);
            }
        }
        return listaResponsa;
    }
    
}
