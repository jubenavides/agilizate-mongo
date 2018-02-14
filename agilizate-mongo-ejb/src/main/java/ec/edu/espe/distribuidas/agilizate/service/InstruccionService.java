/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.InstruccionDAO;
import ec.edu.espe.distribuidas.agilizate.model.Ejercicio;
import ec.edu.espe.distribuidas.agilizate.model.Instruccion;
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
public class InstruccionService {

    @EJB
    private MongoPersistence mp;
    private InstruccionDAO instruccionFacade;

    @PostConstruct
    public void init() {
        this.instruccionFacade = new InstruccionDAO(Instruccion.class, mp.context());
    }

    public List<Instruccion> obtenerTodos() {
        return this.instruccionFacade.find().asList();
    }

    public Instruccion obtenerPorCodigo(ObjectId id) {
        return this.instruccionFacade.get(id);
    }

    public void crear(Instruccion instruccion) {
        this.instruccionFacade.save(instruccion);
    }

    public void modificar(Instruccion instruccion) {
        Instruccion aux = this.instruccionFacade.get(new ObjectId(instruccion.getId()));
        instruccion.setId(aux.getId());
        this.instruccionFacade.save(instruccion);
    }

    public void eliminar(Instruccion instruccion) {
        Instruccion aux = this.instruccionFacade.get(new ObjectId(instruccion.getId()));
        instruccion.setId(aux.getId());
        this.instruccionFacade.delete(instruccion);
    }

    public List<Instruccion> buscarPorEjercicio(String nomEjercicio) {
        return this.instruccionFacade.findByEjercicio(nomEjercicio);
    }
}
