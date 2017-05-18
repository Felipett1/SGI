package com.co.controlador;

import com.co.sgi.Entidad.EntidadContratante;
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
public class ControladorEntidadContratante implements Serializable {

    @EJB
    private IPGenerico pGenerico;
    private List<EntidadContratante> listaEntidadesContratantes;
    private EntidadContratante entidadContratante;
    private final String usuario = "Felipe";
    private static final String TABLA = "EntidadContratante";

    /**
     * Creates a new instance of ControladorEntidadContratante
     */
    public ControladorEntidadContratante() {
        //System.out.println(this.getClass().getSimpleName());
    }

    @PostConstruct
    public void init() {
        listaEntidadesContratantes = (List<EntidadContratante>) (Object) pGenerico.consultar(TABLA);
    }

    //AGREGAR
    public void limpiar() {
        entidadContratante = new EntidadContratante();
    }

    //ACTUALIZAR
    public void seleccionarActualizar_Eliminar(EntidadContratante e) {
        entidadContratante = e;
    }

    public void agregar_actualizar() {
        if (pGenerico.insertar_actualizar(entidadContratante, usuario)) {
            MensajesUI.infoGenerico();
        } else {
            MensajesUI.errorGenerico();
        }
        init();
        actualizarGenerico();
    }

    //ELIMINAR
    public void eliminar() {
        if (pGenerico.eliminar(entidadContratante, usuario)) {
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
    public List<EntidadContratante> getListaEntidadesContratantes() {
        return listaEntidadesContratantes;
    }

    public EntidadContratante getEntidadContratante() {
        return entidadContratante;
    }

    public void setEntidadContratante(EntidadContratante entidadContratante) {
        this.entidadContratante = entidadContratante;
    }
}
