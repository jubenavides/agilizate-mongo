/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.web;

import ec.edu.espe.distribuidas.agilizate.enums.EstadoProgramaClienteEnum;
import ec.edu.espe.distribuidas.agilizate.model.ActividadDia;
import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.model.ProgramaCliente;
import ec.edu.espe.distribuidas.agilizate.service.ActividadDiaService;
import ec.edu.espe.distribuidas.agilizate.service.ClienteService;
import ec.edu.espe.distribuidas.agilizate.service.ProgramaClienteService;
import ec.edu.espe.distribuidas.agilizate.web.util.FacesUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jubenavides
 */
@Named
@ViewScoped
public class ProgramaClienteBean extends BaseBean implements Serializable {

    private Cliente cliente;
    private ProgramaCliente programa;
    private List<ProgramaCliente> programas;
    private ProgramaCliente programaSel;
    private List<ActividadDia> actividades;
    private ActividadDia actividadSel;

    //Boolean para render
    private Boolean enEncontrado;
    private Boolean enBusqueda;
    private Boolean habilitaFormActividaes;

    @Inject
    private ProgramaClienteService programaClienteService;

    @Inject
    private ClienteService clienteService;
    
    @Inject
    private ActividadDiaService actividadService;

    @PostConstruct
    public void init() {
        this.programa = new ProgramaCliente();
        this.enBusqueda = true;
        this.enEncontrado = false;
        this.enAgregar = false;
    }

    public Date CalculaFechaFin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.programa.getFechaInicio());
        calendar.add(Calendar.DAY_OF_YEAR, this.programa.getTotalDuracion());
        return calendar.getTime();
    }

    public void generar() {
        enAgregar = true;
        enBusqueda = false;
        enEncontrado = false;
    }

    public void buscar(String codigo) {
        this.cliente = this.clienteService.obtenerPorCodigo(codigo);
        this.programa.setDescripcion(titulo);
        if (cliente != null) {
            enEncontrado = true;
            this.programas = this.programaClienteService.obtenerPorCliente(cliente);
            this.programa.setEstado(EstadoProgramaClienteEnum.NIN);
        } else {
            FacesUtil.addMessageInfo("No se encontro usuario, verifique el codigo");
        }

    }

    public String generaDescripcion() {
        String cadena;
        cadena = " Programa Cliente " + this.cliente.getNombre() + " " 
                + this.cliente.getApellido() + " Duracion " + this.programa.getTotalDuracion() + " dias";
        return cadena;
    }

    public void crear() {
        try {
            this.programa.setCliente(this.cliente);
            this.programa.setFechaFin(this.CalculaFechaFin());
            this.programa.setDescripcion(generaDescripcion());
            this.programaClienteService.crear(this.programa);
            this.actividadService.generarActividades(this.programa, this.cliente);
            FacesUtil.addMessageInfo("Se agreg\u00f3 un nuevo programa al cliente");
            super.reset();
            this.init();
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al guardar la informaci\u00f3n");
        }

    }

    public void cancelar() {
        this.init();
    }
    
    public void mostrarActividades() {
        this.actividades = this.actividadService.obtenerPorProgramaCliente(this.programaSel);
        System.out.println("Actividaes encontradas: "+ this.actividades );
        this.habilitaFormActividaes = true;
        this.enEncontrado = false;
        this.enBusqueda = false;
    }

    public void eliminar() {
        try {
            this.programaClienteService.eliminar(this.programaSel);
            this.programas = this.programaClienteService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.programaSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ProgramaCliente getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaCliente programa) {
        this.programa = programa;
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

    public List<ProgramaCliente> getProgramas() {
        return programas;
    }

    public void setProgramas(List<ProgramaCliente> programas) {
        this.programas = programas;
    }

    public ProgramaCliente getProgramaSel() {
        return programaSel;
    }

    public void setProgramaSel(ProgramaCliente programaSel) {
        this.programaSel = programaSel;
    }

    public Boolean getHabilitaFormActividaes() {
        return habilitaFormActividaes;
    }

    public void setHabilitaFormActividaes(Boolean habilitaFormActividaes) {
        this.habilitaFormActividaes = habilitaFormActividaes;
    }

    public List<ActividadDia> getActividades() {
        return actividades;
    }

    public ActividadDia getActividadSel() {
        return actividadSel;
    }

    public void setActividadSel(ActividadDia actividadSel) {
        this.actividadSel = actividadSel;
    }
    
    
    

}
