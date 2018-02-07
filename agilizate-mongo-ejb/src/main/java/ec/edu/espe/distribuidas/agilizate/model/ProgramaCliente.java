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
import java.util.Date;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Daniel
 */
@Entity(noClassnameStored = true, value = "programa_cliente")
public class ProgramaCliente extends BaseEntity{

    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer ejercicioFisico;
    private Integer ejercicioMental;
    private Integer ejercicioCombinado;
    private EstadoProgramaClienteEnum estado;
    private Integer totalDuracion;
    @Reference
    private Cliente cliente;

    public ProgramaCliente() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getEjercicioFisico() {
        return ejercicioFisico;
    }

    public void setEjercicioFisico(Integer ejercicioFisico) {
        this.ejercicioFisico = ejercicioFisico;
    }

    public Integer getEjercicioMental() {
        return ejercicioMental;
    }

    public void setEjercicioMental(Integer ejercicioMental) {
        this.ejercicioMental = ejercicioMental;
    }

    public Integer getEjercicioCombinado() {
        return ejercicioCombinado;
    }

    public void setEjercicioCombinado(Integer ejercicioCombinado) {
        this.ejercicioCombinado = ejercicioCombinado;
    }

    public EstadoProgramaClienteEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoProgramaClienteEnum estado) {
        this.estado = estado;
    }

    public Integer getTotalDuracion() {
        return totalDuracion;
    }

    public void setTotalDuracion(Integer totalDuracion) {
        this.totalDuracion = totalDuracion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "ProgramaCliente{" + "descripcion=" + descripcion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", ejercicioFisico=" + ejercicioFisico + ", ejercicioMental=" + ejercicioMental + ", ejercicioCombinado=" + ejercicioCombinado + ", estado=" + estado + ", totalDuracion=" + totalDuracion + ", cliente=" + cliente + '}';
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
        if (!(object instanceof ProgramaCliente)) {
            return false;
        }
        ProgramaCliente other = (ProgramaCliente) object;
        if ((super.id == null && other.id != null) || (super.id != null && !super.id.equals(super.id))) {
            return false;
        }
        return true;
    }

}
