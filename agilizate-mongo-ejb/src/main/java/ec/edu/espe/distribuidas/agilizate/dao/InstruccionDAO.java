/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.dao;

import ec.edu.espe.distribuidas.agilizate.model.Ejercicio;
import ec.edu.espe.distribuidas.agilizate.model.Instruccion;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Daniel
 */
public class InstruccionDAO  extends BasicDAO<Instruccion, ObjectId> {
    
    public InstruccionDAO(Class<Instruccion> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
    
     public List<Instruccion> findByEjercicio(Ejercicio ejercicio){
        Query<Instruccion> q = getDatastore().createQuery(Instruccion.class);
        q.criteria("ejercicio").equal(ejercicio);
        return q.asList();
    }
}
