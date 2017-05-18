package com.co.controlador;

import com.co.sgi.Entidad.Empresa;
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
public class ControladorEmpresa implements Serializable {

    @EJB
    private IPGenerico pGenerico;
    private List<Empresa> listaEmpresas;
    private Empresa empresa;
    private final String usuario = "Felipe";
    private static final String TABLA = "Empresa";

    /**
     * Creates a new instance of ControladorEmpresa
     */
    public ControladorEmpresa() {
        //System.out.println(this.getClass().getSimpleName());
    }

    @PostConstruct
    public void init() {
        listaEmpresas = (List<Empresa>) (Object) pGenerico.consultar(TABLA);
    }

    //AGREGAR
    public void limpiar() {
        empresa = new Empresa();
    }

    //ACTUALIZAR
    public void seleccionarActualizar_Eliminar(Empresa e) {
        empresa = e;
    }

    public void agregar_actualizar() {
        if (pGenerico.insertar_actualizar(empresa, usuario)) {
            MensajesUI.infoGenerico();
        } else {
            MensajesUI.errorGenerico();
        }
        init();
        actualizarGenerico();
    }

    //ELIMINAR
    public void eliminar() {
        if (pGenerico.eliminar(empresa, usuario)) {
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
    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
