/*
 * Hades Cruise
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2017 (c) Hades Cruise Corp.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.SeguimientoDAO;
import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.model.Seguimiento;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;

/**
 *
 * @author Hades Cruise Corp.
 */
@Stateless
@LocalBean
public class SeguimientoService {

    @EJB
    private MongoPersistence mp;
    private SeguimientoDAO seguimientoFacade;

    @PostConstruct
    public void init() {
        this.seguimientoFacade = new SeguimientoDAO(Seguimiento.class, mp.context());
    }

    public List<Seguimiento> obtenerTodos() {
        return this.seguimientoFacade.find().asList();
    }

    public Seguimiento obtenerPorCodigo(ObjectId id) {
        return this.seguimientoFacade.get(id);
    }
    
     public List<Seguimiento> obtenerPorCliente(Cliente cliente) {
        return this.seguimientoFacade.findByCliente(cliente);
    }

    public void crear(Seguimiento seguimiento) {
        this.seguimientoFacade.save(seguimiento);
    }

    public void modificar(Seguimiento seguimiento) {
        Seguimiento aux = this.seguimientoFacade.findOne("id",seguimiento.getId());
        seguimiento.setId(aux.getId());
        this.seguimientoFacade.save(seguimiento);
    }

    public void eliminar(Seguimiento seguimiento) {
        Seguimiento aux = this.seguimientoFacade.findOne("id",seguimiento.getId());
        this.seguimientoFacade.delete(aux);
    }
    
}
