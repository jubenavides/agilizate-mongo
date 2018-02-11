/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.PasatiempoDAO;
import ec.edu.espe.distribuidas.agilizate.model.Pasatiempo;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.bson.types.ObjectId;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class PasatiempoService {

    @EJB
    private MongoPersistence mp;
    private PasatiempoDAO pasatiempoFacade;
    
    @PostConstruct
    public void init() {
        this.pasatiempoFacade = new PasatiempoDAO(Pasatiempo.class, mp.context());
    }

    public List<Pasatiempo> obtenerTodos() {
        return this.pasatiempoFacade.find().asList();
    }

    public Pasatiempo obtenerPorCodigo(ObjectId id) {
        return this.pasatiempoFacade.get(id);
    }

    public void crear(Pasatiempo pasatiempo) {
        this.pasatiempoFacade.save(pasatiempo);
    }
    
    public void modificar(Pasatiempo pasatiempo) {
        Pasatiempo aux = this.pasatiempoFacade.findOne("nombre", pasatiempo.getNombre());
        pasatiempo.setId(aux.getId());
        this.pasatiempoFacade.save(pasatiempo);
    }

    public void eliminar(String nombre) {
        Pasatiempo pasatiempo = this.pasatiempoFacade.findOne("nombre", nombre);
        this.pasatiempoFacade.delete(pasatiempo);
    }
  
}
