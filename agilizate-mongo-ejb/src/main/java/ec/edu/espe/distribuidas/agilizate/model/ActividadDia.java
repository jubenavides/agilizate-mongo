/*
 * Agillizate
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.SA.
 */
package ec.edu.espe.distribuidas.agilizate.model;

import ec.edu.espe.distribuidas.agilizate.enums.CumplidoActividadEnum;
import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import java.util.Date;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Daniel
 */
@Entity(noClassnameStored = true, value = "actividad_dia")
public class ActividadDia extends BaseEntity {

    private Date fecha;
    private Integer duracion;
    @Reference
    private CumplidoActividadEnum cumplido;
    @Reference
    private Ejercicio ejercicio;
    @Reference
    private ProgramaCliente programaCliente;

    public ActividadDia() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public CumplidoActividadEnum getCumplido() {
        return cumplido;
    }

    public void setCumplido(CumplidoActividadEnum cumplido) {
        this.cumplido = cumplido;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public ProgramaCliente getProgramaCliente() {
        return programaCliente;
    }

    public void setProgramaCliente(ProgramaCliente programaCliente) {
        this.programaCliente = programaCliente;
    }

    @Override
    public String toString() {
        return "ActividadDia{" + "fecha=" + fecha + ", duracion=" + duracion + ", cumplido=" + cumplido + ", ejercicio=" + ejercicio + ", programaCliente=" + programaCliente + '}';
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
        if (!(object instanceof ActividadDia)) {
            return false;
        }
        ActividadDia other = (ActividadDia) object;
        if ((super.id == null && other.id != null) || (super.id != null && !super.id.equals(super.id))) {
            return false;
        }
        return true;
    }

}
