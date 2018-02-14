/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.web;

import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.model.ProgramaCliente;
import ec.edu.espe.distribuidas.agilizate.model.Seguimiento;
import ec.edu.espe.distribuidas.agilizate.service.ClienteService;
import ec.edu.espe.distribuidas.agilizate.service.ProgramaClienteService;
import ec.edu.espe.distribuidas.agilizate.service.SeguimientoService;
import ec.edu.espe.distribuidas.agilizate.web.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jolube
 */
@Named
@ViewScoped
public class SeguimientoBean extends BaseBean implements Serializable {

    private Cliente cliente;
    private Seguimiento seguimiento;
    private List<Seguimiento> seguimientos;
    private Seguimiento seguimientoSel;
    private List<ProgramaCliente> programasClientes;
    private String CodCliente;
    //Boolean para render
    private Boolean enEncontrado;
    private Boolean enBusqueda;

    @Inject
    private SeguimientoService seguimientoService;

    @Inject
    private ClienteService clienteService;
    
    @Inject
    private ProgramaClienteService programaClienteService;
    
    @PostConstruct
    public void init() {
        this.seguimiento = new Seguimiento();
        this.enBusqueda = true;
        this.enEncontrado = false;
        this.enAgregar =false;
    }

    public void generar()
    {
        enAgregar = true;
        enBusqueda = false;
        enEncontrado=false;
    }
    
    public void buscar(String codigo) {
        this.cliente = this.clienteService.obtenerPorCodigo(codigo);
        if (cliente != null) {
            enEncontrado = true;
            this.seguimientos = this.seguimientoService.obtenerPorCliente(cliente);
            this.programasClientes = this.programaClienteService.obtenerPorCliente(cliente);
        } else {
            FacesUtil.addMessageInfo("No se encontro usuario, verifique el codigo");
        }
    }

    public void crear() {
        try {
            this.seguimientoService.crear(this.seguimiento);
            FacesUtil.addMessageInfo("Se agreg\u00f3 un nuevo seguimiento al programa");
            super.reset();
            this.init();
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al guardar la informaci\u00f3n");
        }
    }

    public void cancelar()
    {
        this.init();
    }
    
       public void eliminar()
    {
        try {
            this.seguimientoService.eliminar(this.seguimientoSel);
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.seguimientoSel = null;
            this.init();
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado.");
        }
    }
       
    public Cliente getCliente() {
        return cliente;
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public List<Seguimiento> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(List<Seguimiento> seguimientos) {
        this.seguimientos = seguimientos;
    }

    public Seguimiento getSeguimientoSel() {
        return seguimientoSel;
    }

    public void setSeguimientoSel(Seguimiento seguimientoSel) {
        this.seguimientoSel = seguimientoSel;
    }


    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    
    public Boolean getEnEncontrado() {
        return enEncontrado;
    }

    public void setEnEncontrado(Boolean enEncontrado) {
        this.enEncontrado = enEncontrado;
    }

    public Boolean getEnBusqueda() {
        return enBusqueda;
    }

    public void setEnBusqueda(Boolean enBusqueda) {
        this.enBusqueda = enBusqueda;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public List<ProgramaCliente> getProgramasClientes() {
        return programasClientes;
    }

    public void setProgramasClientes(List<ProgramaCliente> programasClientes) {
        this.programasClientes = programasClientes;
    }

    public String getCodCliente() {
        return CodCliente;
    }

    public void setCodCliente(String CodCliente) {
        this.CodCliente = CodCliente;
    }    

}
