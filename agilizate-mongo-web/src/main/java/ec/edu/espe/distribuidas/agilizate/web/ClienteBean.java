/*
 * Agilizate-mongo
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.
 */
package ec.edu.espe.distribuidas.agilizate.web;

import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.service.ClienteService;
import ec.edu.espe.distribuidas.agilizate.web.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.bson.types.ObjectId;

/**
 *
 * @author jubenavides
 */
@Named
@ViewScoped
public class ClienteBean extends BaseBean implements Serializable {
    
    private List<Cliente> clientes;
    
    private Cliente cliente;
    
    private Cliente clienteSel;
    
    @Inject
    private ClienteService clienteService;
    
    @PostConstruct
    public void init() {
        this.clientes = this.clienteService.obtenerTodos();
        this.cliente = new Cliente();
    }
    
    public List<Cliente> getclientes() {
        return clientes;
    }
    
    @Override
    public void agregar() {
        this.cliente = new Cliente();
        super.agregar();
        
    }
    
    @Override
    public void modificar() {
        super.modificar();
        this.cliente = new Cliente();
        this.cliente.setTipoCliente(this.clienteSel.getTipoCliente());
        this.cliente.setCodGenero(this.clienteSel.getCodGenero());
        this.cliente.setCodPasatiempo(this.clienteSel.getCodPasatiempo());
        this.cliente.setNombre(this.clienteSel.getNombre());
        this.cliente.setApellido(this.clienteSel.getApellido());
        this.cliente.setEdad(this.clienteSel.getEdad());
        this.cliente.setCorreo(this.clienteSel.getCorreo());
    }
    
    @Override
    public void detalles() {
        super.detalles();
        this.cliente = this.clienteSel;
    }
    
    public void cancelar() {
        super.reset();
        this.cliente = new Cliente();
    }
    
    public void guardar() {
        try {
            this.clienteService.crear(this.cliente);
            FacesUtil.addMessageInfo("Se agregó el cliente: " + this.cliente.getNombre() + " " + this.cliente.getApellido());
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.cliente = new Cliente();
        this.clientes = this.clienteService.obtenerTodos();
    }

    public void eliminar() {
        try {
            this.clienteService.eliminar(new ObjectId(this.clienteSel.getId()));
            FacesUtil.addMessageInfo("Se elimino el cliente");
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.cliente = new Cliente();
        this.clientes = this.clienteService.obtenerTodos();
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Cliente getClienteSel() {
        return clienteSel;
    }
    
    public void setClienteSel(Cliente clienteSel) {
        this.clienteSel = clienteSel;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }
    
}
