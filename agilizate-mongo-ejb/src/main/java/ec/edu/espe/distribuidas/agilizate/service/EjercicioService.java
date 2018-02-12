/*
 * Agillizate
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.SA.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.EjercicioDAO;
import ec.edu.espe.distribuidas.agilizate.model.Categoria;
import ec.edu.espe.distribuidas.agilizate.model.Dificultad;
import ec.edu.espe.distribuidas.agilizate.model.Ejercicio;
import ec.edu.espe.distribuidas.agilizate.model.Material;
import ec.edu.espe.distribuidas.agilizate.model.Pasatiempo;
import ec.edu.espe.distribuidas.agilizate.model.TipoCliente;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;

/**
 *
 * @author DanielCasa
 */
@Stateless
@LocalBean
public class EjercicioService{

    @EJB
    private MongoPersistence mp;
    private EjercicioDAO ejercicioFacade;
    
    @PostConstruct
    public void init() {
        this.ejercicioFacade = new EjercicioDAO(Ejercicio.class, mp.context());
    }
    public List<Ejercicio> obtenerTodos() {
        return this.ejercicioFacade.find().asList();
    }

    public Ejercicio obtenerPorCodigo(ObjectId id) {
        return this.ejercicioFacade.get(id);
    }

    public void crear(Ejercicio ejercicio) {
        this.ejercicioFacade.save(ejercicio);
    }

     public void modificar(Ejercicio ejercicio) {
        
        this.ejercicioFacade.save(ejercicio);
    }

    public void eliminar(Ejercicio ejercicio) {
        this.ejercicioFacade.delete(ejercicio);
    }

    public List<Ejercicio> obtenerPorDificultad(Dificultad dificultad) {
        return this.ejercicioFacade.findByDificultad(dificultad);
    }
    
    public List<Ejercicio> obtenerPorPasatiempo(Pasatiempo pasatiempo) {
        return this.ejercicioFacade.findByPasatiempo(pasatiempo);
    }
    
    public List<Ejercicio> obtenerTipoCliente(TipoCliente tipoCliente) {
        return this.ejercicioFacade.findByTipCliente(tipoCliente);
    }
    
    public List<Ejercicio> obtenerPorCategoria(Categoria categoria) {
        return this.ejercicioFacade.findByCategoria(categoria);
    }
    
    public List<Ejercicio> obtenerPorMaterial(Material material) {
        return this.ejercicioFacade.findByMaterial(material);
    }
    
}
