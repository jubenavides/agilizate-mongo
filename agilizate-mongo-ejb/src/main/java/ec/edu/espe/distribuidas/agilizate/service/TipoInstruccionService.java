/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.TipoInstruccionDAO;
import ec.edu.espe.distribuidas.agilizate.model.TipoInstruccion;
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
public class TipoInstruccionService {

    @EJB
    private MongoPersistence mp;
    private TipoInstruccionDAO tipoInstruccionFacade;

    @PostConstruct
    public void init() {
        this.tipoInstruccionFacade = new TipoInstruccionDAO(TipoInstruccion.class, mp.context());
    }

    public List<TipoInstruccion> obtenerTodos() {
        return this.tipoInstruccionFacade.find().asList();
    }

    public TipoInstruccion obtenerPorCodigo(ObjectId id) {
        return this.tipoInstruccionFacade.get(id);
    }

    public void crear(TipoInstruccion tipoInstruccion) {
        this.tipoInstruccionFacade.save(tipoInstruccion);
    }

    public void modificar(ObjectId id) {
        TipoInstruccion tipoInstruccion = this.tipoInstruccionFacade.get(id);
        this.tipoInstruccionFacade.save(tipoInstruccion);
    }

    public void eliminar(ObjectId id) {
        TipoInstruccion tipoInstruccion = this.tipoInstruccionFacade.get(id);
        this.tipoInstruccionFacade.delete(tipoInstruccion);
    }
}
