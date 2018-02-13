/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.dao;

import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.model.Seguimiento;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author jubenavides
 */
public class SeguimientoDAO  extends BasicDAO<Seguimiento, ObjectId> {
    
    public SeguimientoDAO(Class<Seguimiento> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    
    public List<Seguimiento> findByCliente(Cliente cliente){
        Query<Seguimiento> q = getDatastore().createQuery(Seguimiento.class);
        q.criteria("cliente").equal(cliente.getCodigo());
        return q.asList();
    }
    
}
