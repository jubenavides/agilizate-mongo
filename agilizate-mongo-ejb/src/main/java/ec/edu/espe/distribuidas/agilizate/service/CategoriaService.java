/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.CategoriaDAO;
import ec.edu.espe.distribuidas.agilizate.enums.CodCategoriaEnum;
import ec.edu.espe.distribuidas.agilizate.model.Categoria;
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
public class CategoriaService {
    
    @EJB
    private MongoPersistence mp;
    private CategoriaDAO categoriaFacade;
    
    @PostConstruct
    public void init() {
        this.categoriaFacade = new CategoriaDAO(Categoria.class, mp.context());
    }

    public List<Categoria> obtenerTodos() {
        return this.categoriaFacade.find().asList();
    }

    public Categoria obtenerPorCodigo(ObjectId id) {
        return this.categoriaFacade.get(id);
    }
    
    public Categoria obtenerPorId(CodCategoriaEnum codigo) {
        Categoria aux = this.categoriaFacade.findOne("codigo", codigo);
        return aux;
    }

    public void crear(Categoria categoria) {
        this.categoriaFacade.save(categoria);
    }

    public void modificar(Categoria categoria) {
        Categoria aux = this.categoriaFacade.findOne("codigo", categoria.getCodigo());
        categoria.setId(aux.getId());
        this.categoriaFacade.save(categoria);
        
    }

    public void eliminar(CodCategoriaEnum codigo) {
        Categoria categoria = this.categoriaFacade.findOne("codigo", codigo);
        this.categoriaFacade.delete(categoria);
    }
}
