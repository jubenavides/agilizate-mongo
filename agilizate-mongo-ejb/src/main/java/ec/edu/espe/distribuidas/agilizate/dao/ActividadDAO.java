/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.dao;

import ec.edu.espe.distribuidas.agilizate.model.ActividadDia;
import ec.edu.espe.distribuidas.agilizate.model.ProgramaCliente;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Daniel
 */
public class ActividadDAO  extends BasicDAO<ActividadDia, ObjectId> {
    
    public ActividadDAO(Class<ActividadDia> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    public List<ActividadDia> findByCliPro(ProgramaCliente programaCliente) {
        Query<ActividadDia> q = getDatastore().createQuery(ActividadDia.class);
        q.criteria("programaCliente").equal(programaCliente);
        return q.asList();
    } 
}
