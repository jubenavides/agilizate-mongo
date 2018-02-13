/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.ProgramaClienteDAO;
import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.model.ProgramaCliente;
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
public class ProgramaClienteService {
    
    @EJB
    private MongoPersistence mp;
    private ProgramaClienteDAO programaClienteFacade;
    
    @PostConstruct
    public void init() {
        this.programaClienteFacade = new ProgramaClienteDAO(ProgramaCliente.class, mp.context());
    }

    public List<ProgramaCliente> obtenerTodos() {
        return this.programaClienteFacade.find().asList();
    }

    public ProgramaCliente obtenerPorCodigo(ObjectId id) {
        return this.programaClienteFacade.get(id);
    }
    
    public List<ProgramaCliente> obtenerPorCliente(Cliente cliente) {
        return this.programaClienteFacade.findByCliente(cliente);
    }

    public void crear(ProgramaCliente programaCliente) {
        this.programaClienteFacade.save(programaCliente);
    }

    public void modificar(ProgramaCliente programaCliente) {
        ProgramaCliente aux = this.programaClienteFacade.get(new ObjectId(programaCliente.getId()));
        programaCliente.setId(aux.getId());
        this.programaClienteFacade.save(programaCliente);
    }

    public void eliminar(ProgramaCliente programaCliente) {
        ProgramaCliente aux = this.programaClienteFacade.get(new ObjectId(programaCliente.getId()));
        programaCliente.setId(aux.getId());
        this.programaClienteFacade.delete(programaCliente);
    }
}
