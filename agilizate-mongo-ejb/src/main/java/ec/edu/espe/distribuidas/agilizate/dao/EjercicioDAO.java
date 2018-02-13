/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.dao;

import ec.edu.espe.distribuidas.agilizate.model.Categoria;
import ec.edu.espe.distribuidas.agilizate.model.Dificultad;
import ec.edu.espe.distribuidas.agilizate.model.Ejercicio;
import ec.edu.espe.distribuidas.agilizate.model.Genero;
import ec.edu.espe.distribuidas.agilizate.model.Material;
import ec.edu.espe.distribuidas.agilizate.model.Pasatiempo;
import ec.edu.espe.distribuidas.agilizate.model.TipoCliente;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Daniel
 */
public class EjercicioDAO extends BasicDAO<Ejercicio, ObjectId> {

    public EjercicioDAO(Class<Ejercicio> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }

    public List<Ejercicio> findByDificultad(Dificultad dificultad) {
        Query<Ejercicio> q = getDatastore().createQuery(Ejercicio.class);
        q.criteria("dificultad").equal(dificultad);
        return q.asList();
    }   

    public List<Ejercicio> findByPasatiempo(Pasatiempo pasatiempo) {
        Query<Ejercicio> q = getDatastore().createQuery(Ejercicio.class);
        q.criteria("pasatiempo").equal(pasatiempo);
        return q.asList();
    }

    public List<Ejercicio> findByTipCliente(TipoCliente tipoCliente) {
        Query<Ejercicio> q = getDatastore().createQuery(Ejercicio.class);
        q.criteria("tipoCliente").equal(tipoCliente);
        return q.asList();
    }

    public List<Ejercicio> findByCategoria(Categoria categoria) {
        Query<Ejercicio> q = getDatastore().createQuery(Ejercicio.class);
        q.criteria("categoria").equal(categoria);
        return q.asList();
    }

    public List<Ejercicio> findByMaterial(Material material) {
        Query<Ejercicio> q = getDatastore().createQuery(Ejercicio.class);
        q.criteria("material").equal(material);
        return q.asList();
    }

     public List<Ejercicio> findByGTcPD(Genero genero,TipoCliente tipoCliente, Pasatiempo pasatiempo, Dificultad dificultad){
        Query<Ejercicio> q = getDatastore().createQuery(Ejercicio.class);
        q.filter("genero", genero).filter("tipoCliente", tipoCliente).filter("pasatiempo", pasatiempo).filter("dificultad", dificultad); 
        return q.asList();
    }
   
}
