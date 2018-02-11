/*
 * Agilizate-mongo
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.
 */
package ec.edu.espe.distribuidas.agilizate.web;

import ec.edu.espe.distribuidas.agilizate.model.TipoCliente;
import ec.edu.espe.distribuidas.agilizate.service.TipoClienteService;
import java.io.Serializable;
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
public class TipoClienteBean extends BaseBean implements Serializable{
    
    private List<TipoCliente> tipoclientes;
    
    private TipoCliente tipoCliente;
    
    private TipoCliente tipoClienteSel;
    
    @Inject
    private TipoClienteService tipoClienteService;
    
    @PostConstruct
    public void init()
    {
        this.tipoclientes = this.tipoClienteService.obtenerTodos();
        this.tipoCliente = new TipoCliente();
    }
    
    @Override
    public void detalles() {
        super.detalles();
        this.tipoCliente = this.tipoClienteSel;
    }
    
    public void cancelar() {
        super.reset();
        this.tipoCliente = new TipoCliente();
    }

    public List<TipoCliente> getTipoclientes() {
        return tipoclientes;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public TipoCliente getTipoClienteSel() {
        return tipoClienteSel;
    }

    public void setTipoClienteSel(TipoCliente tipoClienteSel) {
        this.tipoClienteSel = tipoClienteSel;
    }
    
    
   
}
