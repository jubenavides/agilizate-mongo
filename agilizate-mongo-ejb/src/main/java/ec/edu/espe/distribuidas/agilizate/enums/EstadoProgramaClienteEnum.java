/*
 * Agillizate
 * Aplicaciones Distribuidas
 * NRC: 2434 
 * Tutor: HENRY RAMIRO CORAL CORAL 
 * 2018 (c) Arcentales-Benavides.SA.
 */
package ec.edu.espe.distribuidas.agilizate.enums;

/**
 *
 * @author investigacion
 */
public enum EstadoProgramaClienteEnum {
    NIN("NO INICIADO"),
    ENP("EN PROGRESO"),
    FIN("TERMINADO");
    
    String texto;

    private EstadoProgramaClienteEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
     
}
