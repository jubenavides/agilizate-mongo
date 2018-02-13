/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.model.ActividadDia;
import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.model.ProgramaCliente;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author H201m25
 */
@Stateless
@LocalBean
public class MailService {

    @Resource(name = "java/mail/prueba")
    private Session mailSession;

    @Inject
    private ActividadDiaService actividadService;

    //@Asynchronous
    public void enviarEmail(Cliente cliente, ProgramaCliente programa) throws MessagingException, UnsupportedEncodingException {
        List<ActividadDia> actividades = this.actividadService.obtenerPorProgramaCliente(programa);
        Message message = new MimeMessage(mailSession);
        message.setSubject("Programa");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(cliente.getCorreo(), cliente.getNombre() + " " + cliente.getApellido()));
        StringBuilder contenido = new StringBuilder();
        contenido.append("<h1>Le enviamos su lista de actividades</h1>");
        for (int i = 0; i < actividades.size(); i++) {
            contenido.append("<hr> Para el dia: ");
            contenido.append(actividades.get(i).getFechaFormat());
            contenido.append(" debe realizar el ejercicio: ");
            contenido.append(actividades.get(i).getEjercicio().getNombre());
            contenido.append(" para este ejercicio necesita el material: ");
            contenido.append(actividades.get(i).getEjercicio().getMaterial().getDescripcion());
        }
        message.setContent(contenido.toString(), "text/html");
        Transport.send(message);
    }

}
