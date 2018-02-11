/*
 * Agilizate-mongo
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.
 */
package ec.edu.espe.distribuidas.agilizate.web;

import ec.edu.espe.distribuidas.agilizate.model.Genero;
import ec.edu.espe.distribuidas.agilizate.service.GeneroService;
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
public class GeneroBean extends BaseBean implements Serializable{
    
    private List<Genero> generos;
    
    private Genero genero;
    
    private Genero generoSel;
    
    @Inject
    private GeneroService generoService;
    
    @PostConstruct
    public void init()
    {
        this.generos = this.generoService.obtenerTodos();
        this.genero = new Genero();
    }
    
    @Override
    public void agregar() {
        this.genero = new Genero();
        super.agregar();
    }
    
    @Override
    public void modificar() {
        super.modificar();
        this.genero = new Genero();
        this.genero.setCodigo(this.generoSel.getCodigo());
        this.genero.setDescripcion(this.generoSel.getDescripcion());
    }
    
    public void eliminar() {
        try {
            this.generoService.eliminar(this.generoSel.getCodigo());
            this.generos = this.generoService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.generoSel = null;  
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }
    
    @Override
    public void detalles() {
        super.detalles();
        this.genero = this.generoSel;
    }
    
    public void cancelar() {
        super.reset();
        this.genero = new Genero();
    }
    
    public void guardar() {
        try {
            if (this.enAgregar) {
                this.generoService.crear(this.genero);
                FacesUtil.addMessageInfo("Se agreg\u00f3n genero: " + this.genero.getDescripcion());
            } else {
                this.generoService.modificar(this.genero);
                FacesUtil.addMessageInfo("Se modific\u00f3 genero: " + this.genero.getDescripcion());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.genero = new Genero();
        this.generos = this.generoService.obtenerTodos();
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Genero getGeneroSel() {
        return generoSel;
    }

    public void setGeneroSel(Genero generoSel) {
        this.generoSel = generoSel;
    }

    
   
}
