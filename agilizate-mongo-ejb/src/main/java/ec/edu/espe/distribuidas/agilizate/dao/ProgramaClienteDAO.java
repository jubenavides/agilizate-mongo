/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.dao;

import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.model.ProgramaCliente;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author jubenavides
 */
public class ProgramaClienteDAO extends BasicDAO<ProgramaCliente, ObjectId> {
    
    public ProgramaClienteDAO(Class<ProgramaCliente> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    
    public List<ProgramaCliente> findByCliente(Cliente cliente){
        Query<ProgramaCliente> q = getDatastore().createQuery(ProgramaCliente.class);
        q.criteria("cliente").equal(cliente);
        return q.asList();
    }
    
}
