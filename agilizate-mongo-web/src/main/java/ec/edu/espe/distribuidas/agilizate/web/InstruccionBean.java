/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.web;

import ec.edu.espe.distribuidas.agilizate.model.Ejercicio;
import ec.edu.espe.distribuidas.agilizate.model.Instruccion;
import ec.edu.espe.distribuidas.agilizate.model.TipoInstruccion;
import ec.edu.espe.distribuidas.agilizate.service.EjercicioService;
import ec.edu.espe.distribuidas.agilizate.service.InstruccionService;
import ec.edu.espe.distribuidas.agilizate.service.TipoInstruccionService;
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
public class InstruccionBean extends BaseBean implements Serializable {

    private List<TipoInstruccion> tiposInstruccion;
    private TipoInstruccion tipoInstruccion;
    private TipoInstruccion tipoInstruccionSel;

    private List<Instruccion> instrucciones;
    private List<Ejercicio> ejercicios;

    private String filtro;

    private Instruccion instruccion;
    private Instruccion instruccionSel;

    @Inject
    private InstruccionService instruccionService;
    @Inject
    private TipoInstruccionService tipoInstruccionService;
    @Inject
    private EjercicioService ejercicioService;

    @PostConstruct
    public void init() {
        this.instrucciones = this.instruccionService.obtenerTodos();
        this.ejercicios = this.ejercicioService.obtenerTodos();
        this.tiposInstruccion = this.tipoInstruccionService.obtenerTodos();
        this.instruccion = new Instruccion();
    }

    @Override
    public void agregar() {
        this.instruccion = new Instruccion();
        super.agregar();

    }

    @Override
    public void modificar() {
        super.modificar();
        this.instruccion = new Instruccion();
        this.instruccion.setEjercicio(this.ejercicioService.obtenerPorCodigo(new ObjectId(this.instruccionSel.getCodEjercicio())));
        this.instruccion.setTipoInstruccion(this.tipoInstruccionService.obtenerPorCodigo(new ObjectId(this.instruccionSel.getCodTipoInstruccion())));
        this.instruccion.setRecurso(this.instruccionSel.getRecurso());
        this.instruccion.setCodEjercicio(this.instruccionSel.getCodEjercicio());
        this.instruccion.setCodTipoInstruccion(this.instruccionSel.getCodTipoInstruccion());
    }

    public void eliminar() {
        try {
            this.instruccionService.eliminar(this.instruccionSel);
            this.tiposInstruccion = this.tipoInstruccionService.obtenerTodos(); 
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.tipoInstruccionSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado");
        }
        this.instrucciones = this.instruccionService.obtenerTodos();
    }

    @Override
    public void detalles() {
        super.detalles();
    }

    public void cancelar() {
        super.reset();
        this.tipoInstruccion = new TipoInstruccion();
    }

    public void guardar() {

        try {
            if (this.enAgregar) {
                this.instruccionService.crear(this.instruccion);
                FacesUtil.addMessageInfo("Se agreg\u00f3 la instrucci\u00f3n");
            } else {
                this.instruccionService.modificar(this.instruccion);
                System.out.println(instruccion);
                FacesUtil.addMessageInfo("Se modific\u00f3 el tipo de instrucci\u00f3n: ");
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.instruccion = new Instruccion();
        this.instrucciones = this.instruccionService.obtenerTodos();
        this.instrucciones = this.instruccionService.buscarPorEjercicio(this.instruccion.getEjercicio());
    }

    public void buscar() {
        this.instrucciones = this.instruccionService.buscarPorEjercicio(this.instruccion.getEjercicio());
        this.instruccion.setEjercicio(this.ejercicioService.obtenerPorCodigo(new ObjectId(this.instruccion.getId())));
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

    public List<TipoInstruccion> getTiposInstruccion() {
        return tiposInstruccion;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Instruccion getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(Instruccion instruccion) {
        this.instruccion = instruccion;
    }

    public Instruccion getInstruccionSel() {
        return instruccionSel;
    }

    public void setInstruccionSel(Instruccion instruccionSel) {
        this.instruccionSel = instruccionSel;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

}
