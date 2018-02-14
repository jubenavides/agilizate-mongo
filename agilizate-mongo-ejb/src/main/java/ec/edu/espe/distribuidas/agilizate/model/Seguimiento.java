/*
 * Agillizate
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.SA.
 */
package ec.edu.espe.distribuidas.agilizate.model;

import ec.edu.espe.distribuidas.agilizate.enums.EstadoProgramaClienteEnum;
import ec.edu.espe.distribuidas.nosql.mongo.BaseEntity;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Daniel
 */
@Entity(noClassnameStored = true, value = "seguimiento")
public class Seguimiento extends BaseEntity {

    private Integer avance;
    private Integer totalDuracion;
    private EstadoProgramaClienteEnum estado;
    
    @Reference
    private Cliente cliente;
    
    @Reference
    private ProgramaCliente programaCliente;

    public Seguimiento() {
    }

    public Integer getAvance() {
        return avance;
    }

    public void setAvance(Integer avance) {
        this.avance = avance;
    }

    public Integer getTotalDuracion() {
        return totalDuracion;
    }

    public void setTotalDuracion(Integer totalDuracion) {
        this.totalDuracion = totalDuracion;
    }

    public EstadoProgramaClienteEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoProgramaClienteEnum estado) {
        this.estado = estado;
    }
    
    public ProgramaCliente getProgramaCliente() {
        return programaCliente;
    }

    public void setProgramaCliente(ProgramaCliente programaCliente) {
        this.programaCliente = programaCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Seguimiento{" + "avance=" + avance + ", totalDuracion=" + totalDuracion + ", estado=" + estado + ", programaCliente=" + programaCliente + '}';
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
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((super.id == null && other.id != null) || (super.id != null && !super.id.equals(super.id))) {
            return false;
        }
        return true;
    }

}
