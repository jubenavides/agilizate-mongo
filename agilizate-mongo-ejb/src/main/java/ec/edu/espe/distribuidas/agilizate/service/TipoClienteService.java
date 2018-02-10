/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.TipoClienteDAO;
import ec.edu.espe.distribuidas.agilizate.model.TipoCliente;
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
public class TipoClienteService {
    
    @EJB
    private MongoPersistence mp;
    private TipoClienteDAO tipoClienteFacade;
    
    @PostConstruct
    public void init() {
        this.tipoClienteFacade = new TipoClienteDAO(TipoCliente.class, mp.context());
    }

    public List<TipoCliente> obtenerTodos() {
        return this.tipoClienteFacade.find().asList();
    }

    public TipoCliente obtenerPorCodigo(ObjectId id) {
        return this.tipoClienteFacade.get(id);
    }

    public void crear(TipoCliente tipoCliente) {
        this.tipoClienteFacade.save(tipoCliente);
    }

    public void modificar(ObjectId id) {
        TipoCliente tipoCliente = this.tipoClienteFacade.get(id);
        this.tipoClienteFacade.save(tipoCliente);
    }
    
    public void eliminar(ObjectId id) {
        TipoCliente tipoCliente = this.tipoClienteFacade.get(id);
        this.tipoClienteFacade.delete(tipoCliente);
    }
}
