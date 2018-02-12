/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.GeneroDAO;
import ec.edu.espe.distribuidas.agilizate.enums.CodGeneroEnum;
import ec.edu.espe.distribuidas.agilizate.model.Genero;
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
public class GeneroService {

    @EJB
    private MongoPersistence mp;
    private GeneroDAO generoFacade;

    @PostConstruct
    public void init() {
        this.generoFacade = new GeneroDAO(Genero.class, mp.context());
    }

    public List<Genero> obtenerTodos() {
        return this.generoFacade.find().asList();
    }

    public Genero obtenerPorCodigo(ObjectId id) {
        return this.generoFacade.get(id);
    }

    public Genero obtenerPorId(CodGeneroEnum codigo) {
        Genero aux = this.generoFacade.findOne("codigo", codigo);
        return aux;
    }

    public void crear(Genero genero) {
        this.generoFacade.save(genero);
    }

    public void modificar(Genero genero) {
        Genero aux = this.generoFacade.findOne("codigo", genero.getCodigo());
        genero.setId(aux.getId());
        this.generoFacade.save(genero);
    }

    public void eliminar(CodGeneroEnum codigo) {
        Genero genero = this.generoFacade.findOne("codigo", codigo);
        this.generoFacade.delete(genero);
    }
}
