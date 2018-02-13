/*
 * Agillizate
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.SA.
 */
package ec.edu.espe.distribuidas.agilizate.model;

import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Daniel
 */
@Entity(noClassnameStored = true, value = "instruccion")
public class Instruccion extends BaseEntity {

    private String recurso;
    private String codEjercicio;
    private String codTipoInstruccion;
    @Reference
    private TipoInstruccion tipoInstruccion;
    @Reference
    private Ejercicio ejercicio;

    public Instruccion() {
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getCodEjercicio() {
        return codEjercicio;
    }

    public void setCodEjercicio(String codEjercicio) {
        this.codEjercicio = codEjercicio;
    }

    public String getCodTipoInstruccion() {
        return codTipoInstruccion;
    }

    public void setCodTipoInstruccion(String codTipoInstruccion) {
        this.codTipoInstruccion = codTipoInstruccion;
    }
    
    public TipoInstruccion getTipoInstruccion() {
        return tipoInstruccion;
    }

    public void setTipoInstruccion(TipoInstruccion tipoInstruccion) {
        this.tipoInstruccion = tipoInstruccion;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    @Override
    public String toString() {
        return "Instruccion{" + "recurso=" + recurso + ", codEjercicio=" + codEjercicio + ", codTipoInstruccion=" + codTipoInstruccion + ", tipoInstruccion=" + tipoInstruccion + ", ejercicio=" + ejercicio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (super.id != null ? super.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instruccion)) {
            return false;
        }
        Instruccion other = (Instruccion) object;
        if ((super.id == null && other.id != null) || (super.id != null && !super.id.equals(super.id))) {
            return false;
        }
        return true;
    }
}
