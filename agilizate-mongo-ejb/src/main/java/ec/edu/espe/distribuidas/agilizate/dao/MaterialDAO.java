/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.dao;

import ec.edu.espe.distribuidas.agilizate.model.Material;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author Daniel
 */
public class MaterialDAO  extends BasicDAO<Material, ObjectId> {
    
    public MaterialDAO(Class<Material> objectEntity, Datastore ds) {
        super(objectEntity, ds);
    }
}
