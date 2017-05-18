package com.co.controlador;

import com.co.sgi.Entidad.Empresa;
import com.co.sgi.Entidad.EntidadContratante;
import com.co.sgi.Entidad.Proyecto;
import com.co.sgi.persistencia.interfaz.IPGenerico;
import com.co.utilidades.MensajesUI;
import com.co.utilidades.PrimefacesContextUI;
import java.io.Serializable;
import java.util.Date;
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
public class ControladorProyecto implements Serializable {

    @EJB
    private IPGenerico pGenerico;
    private List<Proyecto> listaProyectos;
    private List<Empresa> listaEmpresas;
    private List<EntidadContratante> listaEntidadContratantes;
    private Proyecto proyecto;
    private final String usuario = "Felipe";
    private static final String TABLA = "Proyecto";

    /**
     * Creates a new instance of ControladorProyecto
     */
    public ControladorProyecto() {
        //System.out.println(this.getClass().getSimpleName());
    }

    @PostConstruct
    public void init() {
        listaProyectos = (List<Proyecto>) (Object) pGenerico.consultar(TABLA);
        llamarLOV();
    }

    public void llamarLOV() {
        listaEmpresas = (List<Empresa>) (Object) pGenerico.consultar("Empresa");
        listaEntidadContratantes = (List<EntidadContratante>) (Object) pGenerico.consultar("EntidadContratante");
    }

    //AGREGAR
    public void limpiar() {
        proyecto = new Proyecto();
    }

    //ACTUALIZAR
    public void seleccionarActualizar_Eliminar(Proyecto e) {
        proyecto = e;
    }

    public void agregar_actualizar() {
        if (proyecto.getFechaFinal().compareTo(new Date()) >= 0) {
            proyecto.setEstado(true);
        } else {
            proyecto.setEstado(false);
        }

        if (pGenerico.insertar_actualizar(proyecto, usuario)) {
            MensajesUI.infoGenerico();
        } else {
            MensajesUI.errorGenerico();
        }
        init();
        actualizarGenerico();
    }

    //ELIMINAR
    public void eliminar() {
        if (pGenerico.eliminar(proyecto, usuario)) {
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

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public List<EntidadContratante> getListaEntidadContratantes() {
        return listaEntidadContratantes;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
