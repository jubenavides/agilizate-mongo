/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.DificultadDAO;
import ec.edu.espe.distribuidas.agilizate.model.Dificultad;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;

/**
 *
 * @author jubenavides
 */
@Stateless
@LocalBean
public class DificultadService {
     
    @EJB
    private MongoPersistence mp;
    private DificultadDAO dificultadFacade;
    
    @PostConstruct
    public void init() {
        this.dificultadFacade = new DificultadDAO(Dificultad.class, mp.context());
    }

    public List<Dificultad> obtenerTodos() {
        return this.dificultadFacade.find().asList();
    }

    public Dificultad obtenerPorCodigo(ObjectId id) {
        return this.dificultadFacade.get(id);
    }

    public void crear(Dificultad dificultad) {
        this.dificultadFacade.save(dificultad);
    }

    public void modificar(ObjectId id) {
        Dificultad dificultad = this.dificultadFacade.get(id);
        this.dificultadFacade.save(dificultad);
    }

    public void eliminar(ObjectId id) {
        Dificultad dificultad = this.dificultadFacade.get(id);
        this.dificultadFacade.delete(dificultad);
    }
}
