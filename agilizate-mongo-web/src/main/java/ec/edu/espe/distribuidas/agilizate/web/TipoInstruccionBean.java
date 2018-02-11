/*
 * Agilizate-mongo
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.
 */
package ec.edu.espe.distribuidas.agilizate.web;

import ec.edu.espe.distribuidas.agilizate.model.TipoInstruccion;
import ec.edu.espe.distribuidas.agilizate.service.TipoInstruccionService;
import ec.edu.espe.distribuidas.agilizate.web.util.FacesUtil;
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
public class TipoInstruccionBean extends BaseBean implements Serializable{
    
    private List<TipoInstruccion> tipoInstrucciones;
    
    private TipoInstruccion tipoInstruccion;
    
    private TipoInstruccion tipoInstruccionSel;
    
    @Inject
    private TipoInstruccionService tipoInstruccionService;
    
    @PostConstruct
    public void init()
    {
        this.tipoInstrucciones = this.tipoInstruccionService.obtenerTodos();
        this.tipoInstruccion = new TipoInstruccion();
    }
    
    @Override
    public void agregar() {
        this.tipoInstruccion = new TipoInstruccion();
        super.agregar();
    }
    
    @Override
    public void modificar() {
        super.modificar();
        this.tipoInstruccion = new TipoInstruccion();
        this.tipoInstruccion.setCodigo(this.tipoInstruccionSel.getCodigo());
        this.tipoInstruccion.setNombre(this.tipoInstruccionSel.getNombre());
        this.tipoInstruccion.setDescripcion(this.tipoInstruccionSel.getDescripcion());
    }
    
    public void eliminar() {
        try {
            this.tipoInstruccionService.eliminar(this.tipoInstruccionSel.getCodigo());
            this.tipoInstrucciones = this.tipoInstruccionService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.tipoInstruccionSel = null;  
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }
    
    @Override
    public void detalles() {
        super.detalles();
        this.tipoInstruccion = this.tipoInstruccionSel;
    }
    
    public void cancelar() {
        super.reset();
        this.tipoInstruccion = new TipoInstruccion();
    }
    
    public void guardar() {
        try {
            if (this.enAgregar) {
                this.tipoInstruccionService.crear(this.tipoInstruccion);
                FacesUtil.addMessageInfo("Se agreg\u00f3n tipoInstruccion: " + this.tipoInstruccion.getDescripcion());
            } else {
                this.tipoInstruccionService.modificar(this.tipoInstruccion);
                FacesUtil.addMessageInfo("Se modific\u00f3 tipoInstruccion: " + this.tipoInstruccion.getDescripcion());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.tipoInstruccion = new TipoInstruccion();
        this.tipoInstrucciones = this.tipoInstruccionService.obtenerTodos();
    }

    public List<TipoInstruccion> getTipoInstrucciones() {
        return tipoInstrucciones;
    }

    public TipoInstruccion getTipoInstruccion() {
        return tipoInstruccion;
    }

    public void setTipoInstruccion(TipoInstruccion tipoInstruccion) {
        this.tipoInstruccion = tipoInstruccion;
    }

    public TipoInstruccion getTipoInstruccionSel() {
        return tipoInstruccionSel;
    }

    public void setTipoInstruccionSel(TipoInstruccion tipoInstruccionSel) {
        this.tipoInstruccionSel = tipoInstruccionSel;
    }

}
