/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.agilizate.service;

import ec.edu.espe.distribuidas.agilizate.dao.ActividadDAO;
import ec.edu.espe.distribuidas.agilizate.enums.CumplidoActividadEnum;
import ec.edu.espe.distribuidas.agilizate.model.ActividadDia;
import ec.edu.espe.distribuidas.agilizate.model.Cliente;
import ec.edu.espe.distribuidas.agilizate.model.Dificultad;
import ec.edu.espe.distribuidas.agilizate.model.Ejercicio;
import ec.edu.espe.distribuidas.agilizate.model.ProgramaCliente;
import ec.edu.espe.distribuidas.nosql.mongo.MongoPersistence;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import org.bson.types.ObjectId;

/**
 *
 * @author jubenavides
 */
@Stateless
@LocalBean
public class ActividadDiaService {

    @EJB
    private MongoPersistence mp;
    private ActividadDAO actividadFacade;
    private MailService mail;

    @Inject
    private EjercicioService ejercicioService;

    @Inject
    private DificultadService dificultadService;

    @EJB
    private MailService mailService;

    @PostConstruct
    public void init() {
        this.actividadFacade = new ActividadDAO(ActividadDia.class, mp.context());
    }

    public List<ActividadDia> obtenerTodos() {
        return this.actividadFacade.find().asList();
    }

    public ActividadDia obtenerPorCodigo(ObjectId id) {
        return this.actividadFacade.get(id);
    }

    public void crear(ActividadDia categoria) {
        this.actividadFacade.save(categoria);
    }

    public List<ActividadDia> obtenerPorProgramaCliente(ProgramaCliente programa) {
        return this.actividadFacade.findByCliPro(programa);
    }

    public void generarActividades(ProgramaCliente programa, Cliente cliente) throws MessagingException, UnsupportedEncodingException {
        System.out.println("Programa--" + programa);
        List<Dificultad> dificultades = this.dificultadService.obtenerTodos();
        List<Ejercicio> ejerciciosEspecificosFaciles = this.ejercicioService.obtenerPorGTcPD(cliente.getGenero(), cliente.getTipoCliente(), cliente.getPasatiempo(), dificultades.get(0));
        System.out.println("Especificos Faciles: " + ejerciciosEspecificosFaciles.size() + "---" + ejerciciosEspecificosFaciles);
//        List<Ejercicio> ejerciciosGeneroOtrosFaciles = this.ejercicioService.obtenerPorGTcPD(programa.getCliente().getCodGenero(), programa.getCliente().getCodTipoCliente(), programa.getCliente().getCodPasatiempo(), codDificultad);
//        System.out.println("Genericos Faciles: " + ejerciciosGeneroOtrosFaciles.size() + "---" + ejerciciosGeneroOtrosFaciles);
//        List<Ejercicio> ejerciciosGeneroOtrosPasaFaciles = this.ejercicioService.obtenerPorGTcPD(programa.getCliente().getCodGenero(), programa.getCliente().getCodTipoCliente(), 4, codDificultad);
//        System.out.println("Genericos Faciles: " + ejerciciosGeneroOtrosPasaFaciles.size() + "---" + ejerciciosGeneroOtrosPasaFaciles);
//
//        codDificultad = 2;
//        List<Ejercicio> ejerciciosEspecificosMedios = this.ejercicioService.obtenerPorGTcPD(programa.getCliente().getCodGenero(), programa.getCliente().getCodTipoCliente(), programa.getCliente().getCodPasatiempo(), codDificultad);
//        System.out.println("Especificos Medios:" + ejerciciosEspecificosMedios.size() + "---" + ejerciciosEspecificosMedios);
//        List<Ejercicio> ejerciciosGeneroOtrosMedios = this.ejercicioService.obtenerPorGTcPD(programa.getCliente().getCodGenero(), programa.getCliente().getCodTipoCliente(), programa.getCliente().getCodPasatiempo(), codDificultad);
//        System.out.println("Genericos Medios: " + ejerciciosGeneroOtrosMedios.size() + "---" + ejerciciosGeneroOtrosMedios);
//        List<Ejercicio> ejerciciosGeneroOtrosPasaMedios = this.ejercicioService.obtenerPorGTcPD(programa.getCliente().getCodGenero(), programa.getCliente().getCodTipoCliente(), 4, codDificultad);
//        System.out.println("Genericos Faciles: " + ejerciciosGeneroOtrosPasaMedios.size() + "---" + ejerciciosGeneroOtrosPasaMedios);
//
//        codDificultad = 3;
//        List<Ejercicio> ejerciciosEspecificosDificiles = this.ejercicioService.obtenerPorGTcPD(programa.getCliente().getCodGenero(), programa.getCliente().getCodTipoCliente(), programa.getCliente().getCodPasatiempo(), codDificultad);
//        System.out.println("Especificos Dificiles: " + ejerciciosEspecificosDificiles.size() + "---" + ejerciciosEspecificosDificiles);
//        List<Ejercicio> ejerciciosGeneroOtrosDificiles = this.ejercicioService.obtenerPorGTcPD(programa.getCliente().getCodGenero(), programa.getCliente().getCodTipoCliente(), programa.getCliente().getCodPasatiempo(), codDificultad);
//        System.out.println("Genericos Dificiles: " + ejerciciosGeneroOtrosDificiles.size() + "---" + ejerciciosGeneroOtrosDificiles);
//        List<Ejercicio> ejerciciosGeneroOtrosPasaDificiles = this.ejercicioService.obtenerPorGTcPD(programa.getCliente().getCodGenero(), programa.getCliente().getCodTipoCliente(), 4, codDificultad);
//        System.out.println("Genericos Dificiles: " + ejerciciosGeneroOtrosPasaDificiles.size() + "---" + ejerciciosGeneroOtrosPasaDificiles);
//

//
        List<Ejercicio> ejerciciosTotales = new ArrayList<>();
        ejerciciosTotales.addAll(ejerciciosEspecificosFaciles);
//        ejerciciosTotales.addAll(ejerciciosGeneroOtrosFaciles);
//        ejerciciosTotales.addAll(ejerciciosGeneroOtrosPasaFaciles);
//        ejerciciosTotales.addAll(ejerciciosEspecificosMedios);
//        ejerciciosTotales.addAll(ejerciciosGeneroOtrosMedios);
//        ejerciciosTotales.addAll(ejerciciosGeneroOtrosPasaMedios);
//        ejerciciosTotales.addAll(ejerciciosEspecificosDificiles);
//        ejerciciosTotales.addAll(ejerciciosGeneroOtrosDificiles);
//        ejerciciosTotales.addAll(ejerciciosGeneroOtrosPasaDificiles);
//
//        System.out.println("Ejercicios Faciles total: " + ejerciciosTotales.size() + "---" + ejerciciosTotales);
//
        SecureRandom sr = new SecureRandom();

//        
        for (int i = 0; i < (int) programa.getTotalDuracion(); i++) {
            for (int j = 0; j < 3; j++) {
                ActividadDia actividad = new ActividadDia();
                actividad.setProgramaCliente(programa);
                Ejercicio aux;
                int ejer;
                ejer = sr.nextInt(ejerciciosTotales.size());
                aux = ejerciciosTotales.get(ejer);
                actividad.setCumplido(CumplidoActividadEnum.NO);
                actividad.setFecha(CalculaFechaFin(programa, j));
                actividad.setEjercicio(aux);
                System.out.println("Ya casi: " + i + "---" + j + "------" + actividad);
                crear(actividad);
            }
        }

        try {
            this.mailService.enviarEmail(cliente, programa);
            System.out.println("Fin del test");
        } catch (Exception ex) {
            System.out.println("Ocurrio un error al enviar el mail");
            Logger.getLogger(ActividadDiaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public Date CalculaFechaFin(ProgramaCliente programa, Integer TotalDuracion) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(programa.getFechaInicio());
        calendar.add(Calendar.DAY_OF_YEAR, TotalDuracion);
        return calendar.getTime();
    }

}
