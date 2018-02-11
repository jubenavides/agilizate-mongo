/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.ClienteDAO;
import ec.edu.espe.distribuidas.agilizate.model.Cliente;
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
public class ClienteService {
    
    @EJB
    private MongoPersistence mp;
    private ClienteDAO clienteFacade;
    
    @PostConstruct
    public void init() {
        this.clienteFacade = new ClienteDAO(Cliente.class, mp.context());
    }

    public List<Cliente> obtenerTodos() {
        return this.clienteFacade.find().asList();
    }

    public Cliente obtenerPorCodigo(ObjectId id) {
        return this.clienteFacade.get(id);
    }

    public void crear(Cliente cliente) {
        this.clienteFacade.save(cliente);
    }

    public void modificar(ObjectId id) {
        Cliente cliente = this.clienteFacade.get(id);
        this.clienteFacade.save(cliente);
    }

    public void eliminar(ObjectId id) {
        Cliente categoria = this.clienteFacade.get(id);
        this.clienteFacade.delete(categoria);
    }
}
