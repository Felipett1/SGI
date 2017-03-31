package com.co.controlador;

import com.co.sgi.Entidad.Empresa;
import com.co.sgi.persistencia.interfaz.IPEmpresa;
import com.co.sgi.persistencia.interfaz.IPGenerico;
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
    private IPEmpresa pEmpresa;
    @EJB
    private IPGenerico pGenerico;

    private List<Empresa> listaEmpresas;
    private Empresa empresa;

    /**
     * Creates a new instance of ControladorEmpresa
     */
    public ControladorEmpresa() {
    }

    @PostConstruct
    public void init() {
        listaEmpresas = (List<Empresa>) (Object) pGenerico.consultar("Empresa");
    }

    public void prueba() {
        System.out.println("INICIO");
        Empresa e = new Empresa(null, 102, "PRAGMA S.A");
        pGenerico.insertar_actualizar(e, "Felipe");
        e = listaEmpresas.get(0);
        e.setCentroCosto(110);
        pGenerico.insertar_actualizar(e, "Felipe");
    }

    //GETTER AND SETTER
    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
