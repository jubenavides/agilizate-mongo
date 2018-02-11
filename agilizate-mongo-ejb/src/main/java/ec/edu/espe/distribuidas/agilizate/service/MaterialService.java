/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.MaterialDAO;
import ec.edu.espe.distribuidas.agilizate.model.Material;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;

/**
 *
 * @author Hp
 */
@Stateless
@LocalBean
public class MaterialService {
     @EJB
    private MongoPersistence mp;
    private MaterialDAO materialFacade;
    
    @PostConstruct
    public void init() {
        this.materialFacade = new MaterialDAO(Material.class, mp.context());
    }

    public List<Material> obtenerTodos() {
        return this.materialFacade.find().asList();
    }

    public Material obtenerPorCodigo(ObjectId id) {
        return this.materialFacade.get(id);
    }

    public void crear(Material material) {
        this.materialFacade.save(material);
    }
    
    public void modificar(Material material) {
        Material aux = this.materialFacade.findOne("codigo", material.getCodigo());
        material.setId(aux.getId());
        this.materialFacade.save(material);
    }

    public void eliminar(String codigo) {
        Material material = this.materialFacade.findOne("codigo", codigo);
        this.materialFacade.delete(material);
    }
    
}
