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
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;


/**
 *
 * @author Daniel
 */
@Entity(noClassnameStored = true, value = "cliente")
public class Cliente extends BaseEntity {
    
    @Indexed(options = @IndexOptions(name = "codigo_codigoUIdx", unique = true))
    private String codigo;
    private String codTipoCliente;
    private String codGenero;
    private String codPasatiempo;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String correo;
    @Reference
    private TipoCliente tipoCliente;
    @Reference
    private Genero genero;
    @Reference
    private Pasatiempo pasatiempo;

    public Cliente() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodGenero() {
        return codGenero;
    }

    public void setCodGenero(String codGenero) {
        this.codGenero = codGenero;
    }

    public String getCodTipoCliente() {
        return codTipoCliente;
    }

    public void setCodTipoCliente(String codTipoCliente) {
        this.codTipoCliente = codTipoCliente;
    }

    public String getCodPasatiempo() {
        return codPasatiempo;
    }

    public void setCodPasatiempo(String codPasatiempo) {
        this.codPasatiempo = codPasatiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Pasatiempo getPasatiempo() {
        return pasatiempo;
    }

    public void setPasatiempo(Pasatiempo pasatiempo) {
        this.pasatiempo = pasatiempo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "codigo=" + codigo + ", codTipoCliente=" + codTipoCliente + ", codGenero=" + codGenero + ", codPasatiempo=" + codPasatiempo + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", correo=" + correo + ", tipoCliente=" + tipoCliente + ", genero=" + genero + ", pasatiempo=" + pasatiempo + '}';
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((super.id == null && other.id != null) || (super.id != null && !super.id.equals(super.id))) {
            return false;
        }
        return true;
    }

    
}
