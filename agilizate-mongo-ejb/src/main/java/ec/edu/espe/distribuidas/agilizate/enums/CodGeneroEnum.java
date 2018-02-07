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
public enum CodGeneroEnum {
    M("MASCULINO"),
    F("FEMENINO"),
    O("OTRO");
    
    String texto;

    private CodGeneroEnum(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

}
