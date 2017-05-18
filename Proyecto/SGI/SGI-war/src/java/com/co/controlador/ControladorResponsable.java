package com.co.controlador;

import com.co.sgi.Entidad.Proyecto;
import com.co.sgi.Entidad.Responsable;
import com.co.sgi.persistencia.interfaz.IPGenerico;
import com.co.utilidades.MensajesUI;
import com.co.utilidades.PrimefacesContextUI;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Felipe Triviño
 */
@ManagedBean
@ViewScoped
public class ControladorResponsable implements Serializable {

    @EJB
    private IPGenerico pGenerico;
    private List<Responsable> listaResponsable;
    private List<Proyecto> listaProyectos;
    private Responsable responsable;
    private final String usuario = "Felipe";
    private static final String TABLA = "Responsable";

    /**
     * Creates a new instance of ControladorProyecto
     */
    public ControladorResponsable() {
        //System.out.println(this.getClass().getSimpleName());
    }

    @PostConstruct
    public void init() {
        listaResponsable = (List<Responsable>) (Object) pGenerico.consultar(TABLA);
        llamarLOV();
    }

    public void llamarLOV() {
        listaProyectos = (List<Proyecto>) (Object) pGenerico.consultar("Proyecto");
    }

    //AGREGAR
    public void limpiar() {
        responsable = new Responsable();
    }

    //ACTUALIZAR
    public void seleccionarActualizar_Eliminar(Responsable e) {
        responsable = e;
    }

    public void agregar_actualizar() {
        if (pGenerico.insertar_actualizar(responsable, usuario)) {
            MensajesUI.infoGenerico();
        } else {
            MensajesUI.errorGenerico();
        }
        init();
        actualizarGenerico();
    }

    //ELIMINAR
    public void eliminar() {
        if (pGenerico.eliminar(responsable, usuario)) {
            MensajesUI.infoGenerico();
        } else {
            MensajesUI.errorGenerico();
        }
        init();
        actualizarGenerico();
    }

    public void actualizarGenerico() {
        PrimefacesContextUI.actualizar("principalForm:dtbDatos");
    }

    //GETTER AND SETTER
    public List<Proyecto> getListaProyectos() {
        return listaProyectos;
    }

    public List<Responsable> getListaResponsable() {
        return listaResponsable;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

}
