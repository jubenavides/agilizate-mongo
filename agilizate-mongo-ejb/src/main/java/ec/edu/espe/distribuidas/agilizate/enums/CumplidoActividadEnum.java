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
public enum CumplidoActividadEnum {
    SI("CUMPLIDO"),
    NO("NO CUMPLIDO");
    
    String texto;

    private CumplidoActividadEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
    
}
