/*
 * Agilizate-mongo
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.
 */
package ec.edu.espe.distribuidas.agilizate.web;

import ec.edu.espe.distribuidas.agilizate.model.Categoria;
import ec.edu.espe.distribuidas.agilizate.service.CategoriaService;
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
public class CategoriaBean extends BaseBean implements Serializable{
    
    private List<Categoria> categorias;
    
    private Categoria categoria;
    
    private Categoria categoriaSel;
    
    @Inject
    private CategoriaService categoriaService;
    
    @PostConstruct
    public void init()
    {
        this.categorias = this.categoriaService.obtenerTodos();
        this.categoria = new Categoria();
    }
    
    public List<Categoria> getTiposCamarote(){
        return categorias;
    }
    
    @Override
    public void agregar() {
        this.categoria = new Categoria();
        super.agregar();
    }
    
    @Override
    public void modificar() {
        super.modificar();
        this.categoria = new Categoria();
        this.categoria.setCodigo(this.categoriaSel.getCodigo());
        this.categoria.setDescripcion(this.categoriaSel.getDescripcion());
    }
    
    public void eliminar() {
        try {
            this.categoriaService.eliminar(this.categoriaSel.getCodigo());
            this.categorias = this.categoriaService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro ");
            this.categoriaSel = null;  
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }
    
    @Override
    public void detalles() {
        super.detalles();
        this.categoria = this.categoriaSel;
    }
    
    public void cancelar() {
        super.reset();
        this.categoria = new Categoria();
    }
    
    public void guardar() {
        try {
            if (this.enAgregar) {
                this.categoriaService.crear(this.categoria);
                FacesUtil.addMessageInfo("Se agreg\u00f3n categoria: " + this.categoria.getDescripcion());
            } else {
                this.categoriaService.modificar(this.categoria);
                FacesUtil.addMessageInfo("Se modific\u00f3 categoria con c\u00f3digo: " + this.categoria.getDescripcion());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.categoria = new Categoria();
        this.categorias = this.categoriaService.obtenerTodos();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoriaSel() {
        return categoriaSel;
    }

    public void setCategoriaSel(Categoria categoriaSel) {
        this.categoriaSel = categoriaSel;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
   
}
