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
    private List<Instruccion> instrucciones;

    private List<Ejercicio> ejercicios;
    private Ejercicio ejercicio;

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
        this.ejercicios = this.ejercicioService.obtenerTodos();
        this.tiposInstruccion = this.tipoInstruccionService.obtenerTodos();
        this.instrucciones = this.instruccionService.buscarPorEjercicio(ejercicios.get(0).getNombre());
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
        this.instruccion.setRecurso(this.instruccionSel.getRecurso());
        this.instruccion.setCodEjercicio(this.instruccionSel.getCodEjercicio());
        this.instruccion.setCodTipoInstruccion(this.instruccionSel.getCodTipoInstruccion());
        this.instruccion.setId(this.instruccionSel.getId());
    }

    public void eliminar() {
        try {
            this.instruccionService.eliminar(this.instruccionSel);
            this.tiposInstruccion = this.tipoInstruccionService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.instruccionSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado");
        }
        this.instrucciones = this.instruccionService.buscarPorEjercicio(ejercicio.getNombre());
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
                this.instruccion.setCodEjercicio(this.instruccionSel.getCodEjercicio());
                this.instruccionService.modificar(this.instruccion);
                FacesUtil.addMessageInfo("Se modific\u00f3 el tipo de instrucci\u00f3n: ");
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        String auxBuscar = this.ejercicio.getId();
        super.reset();
        buscar(auxBuscar);
    }

    public void buscar(String codigoEjercicio) {
        this.ejercicio = this.ejercicioService.obtenerPorCodigo(new ObjectId(codigoEjercicio));
        this.instrucciones = this.instruccionService.buscarPorEjercicio(ejercicio.getNombre());
    }

    public TipoInstruccion getTipoInstruccion() {
        return tipoInstruccion;
    }

    public void setTipoInstruccion(TipoInstruccion tipoInstruccion) {
        this.tipoInstruccion = tipoInstruccion;
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
