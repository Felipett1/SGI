package com.co.controlador;

import com.co.sgi.Entidad.Articulo;
import com.co.sgi.Entidad.Categoria;
import com.co.sgi.Entidad.ArtEquiCom;
import com.co.sgi.Entidad.ArtLab;
import com.co.sgi.Entidad.ArtMbsMje;
import com.co.sgi.Entidad.ArtMbsOfc;
import com.co.sgi.Entidad.ArtPer;
import com.co.sgi.Entidad.ArtTop;
import com.co.sgi.Entidad.ArtVehi;
import com.co.sgi.persistencia.interfaz.IPArticulo;
import com.co.sgi.persistencia.interfaz.IPGenerico;
import com.co.utilidades.ControlNavegacion;
import com.co.utilidades.MensajesUI;
import com.co.utilidades.PrimefacesContextUI;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Felipe Triviño
 */
@ManagedBean
@ViewScoped
public class ControladorArticulo implements Serializable {

    @EJB
    private IPGenerico pGenerico;
    @EJB
    private IPArticulo pArticulo;
    private List<Articulo> listaArticulos;
    private List<Categoria> listacaCategorias;
    private List<String> clasificaciones, marcas;
    private Articulo articulo;
    private ControladorListasValores clv;
    private ControlNavegacion cn;
    private UploadedFile factura;
    private String rutaGeneral, ruta, verFactura, accion, estadoFactura;

    //Tipos de articulos
    private ArtEquiCom equiCom;
    private ArtMbsMje mbsMje;
    private ArtMbsOfc mbsOfc;
    private ArtLab lab;
    private ArtPer per;
    private ArtTop top;
    private ArtVehi vehi;

    private final String usuario = "Felipe";
    private static final String TABLA = "Articulo";
    private static final String LOV_CLASIFICACIONES = "clasificaciones";
    private static final String LOV_MARCAS = "marcas";

    /**
     * Creates a new instance of ControladorProyecto
     */
    public ControladorArticulo() {
        //System.out.println(this.getClass().getSimpleName());
    }

    @PostConstruct
    public void init() {
        FacesContext x = FacesContext.getCurrentInstance();
        HttpSession ses = (HttpSession) x.getExternalContext().getSession(false);
        clv = ((ControladorListasValores) x.getApplication().evaluateExpressionGet(x, "#{controladorListasValores}", ControladorListasValores.class));
        cn = ((ControlNavegacion) x.getApplication().evaluateExpressionGet(x, "#{controlNavegacion}", ControlNavegacion.class));
        listaArticulos = (List<Articulo>) (Object) pGenerico.consultar(TABLA);
        llamarLOV();
        String r = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        rutaGeneral = r.substring(0, r.indexOf("WEB-INF")) + "recursos/verFactura/";
    }

    public void cargarArchivo(FileUploadEvent event) {
        estadoFactura = "A";
        this.factura = event.getFile();
    }

    public void llamarLOV() {
        listacaCategorias = (List<Categoria>) (Object) pGenerico.consultar("Categoria");
    }

    public void lovDinamico(int categoria) {
        if (categoria != 0) {
            clasificaciones = clv.listaValor(clv.getProp().getProperty(LOV_CLASIFICACIONES), String.valueOf(categoria));
            marcas = clv.listaValor(clv.getProp().getProperty(LOV_MARCAS), String.valueOf(categoria));
            switch (categoria) {
                case 1:
                    this.equiCom = new ArtEquiCom();
                    break;
                case 2:
                    this.mbsMje = new ArtMbsMje();
                    break;
                case 3:
                    this.mbsOfc = new ArtMbsOfc();
                    break;
                case 4:
                    this.lab = new ArtLab();
                    break;
                case 5:
                    this.top = new ArtTop();
                    break;
                case 6:
                    this.per = new ArtPer();
                    break;
                case 7:
                    this.vehi = new ArtVehi();
                    break;
            }
        }
    }

    //AGREGAR
    public void limpiar() {
        articulo = new Articulo();
        cn.setUrlContenidoDinamico("");
        factura = null;
        accion = "I";
    }

    //ACTUALIZAR
    public void seleccionarActualizar_Eliminar(Articulo e, String act) {
        articulo = e;
        accion = act;
        factura = null;
        if (accion.equals("A") && articulo.getFacturaDigital() != null) {
            estadoFactura = "S";
        } else {
            estadoFactura = "N";
        }
        int categoria = articulo.getCategoria().getSecuencia().intValue();
        lovDinamico(categoria);
        Object resultado = pArticulo.consultarCategoria(articulo.getSecuencia(), categoria);
        switch (categoria) {
            case 1:
                this.equiCom = (ArtEquiCom) resultado;
                break;
            case 2:
                this.mbsMje = (ArtMbsMje) resultado;
                break;
            case 3:
                this.mbsOfc = (ArtMbsOfc) resultado;
                break;
            case 4:
                this.lab = (ArtLab) resultado;
                break;
            case 5:
                this.top = (ArtTop) resultado;
                break;
            case 6:
                this.per = (ArtPer) resultado;
                break;
            case 7:
                this.vehi = (ArtVehi) resultado;
                break;
        }
        try {
            cn.formularioArticuloDinamico(articulo.getCategoria().getSecuencia().intValue());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void agregar_actualizar() {
        if (factura == null && accion.equals("I")) {
            MensajesUI.error("Es obligatorio agregar la factura en PDF.");
        } else {
            try {
                if (estadoFactura.equals("A")) {
                    articulo.setFacturaDigital(IOUtils.toByteArray(factura.getInputstream()));
                }
                if (accion.equals("I")) {
                    articulo.setPrestado(false);
                }
                boolean exitoso = false;
                Articulo art = (Articulo) pGenerico.insertar_actualizarObjeto(articulo, usuario);
                if (art != null) {
                    switch (articulo.getCategoria().getSecuencia().intValue()) {
                        case 1:
                            this.equiCom.setArticulo(art);
                            exitoso = pGenerico.insertar_actualizar(this.equiCom, usuario);
                            break;
                        case 2:
                            this.mbsMje.setArticulo(art);
                            exitoso = pGenerico.insertar_actualizar(this.mbsMje, usuario);
                            break;
                        case 3:
                            this.mbsOfc.setArticulo(art);
                            exitoso = pGenerico.insertar_actualizar(this.mbsOfc, usuario);
                            break;
                        case 4:
                            this.lab.setArticulo(art);
                            exitoso = pGenerico.insertar_actualizar(this.lab, usuario);
                            break;
                        case 5:
                            this.top.setArticulo(art);
                            exitoso = pGenerico.insertar_actualizar(this.top, usuario);
                            break;
                        case 6:
                            this.per.setArticulo(art);
                            exitoso = pGenerico.insertar_actualizar(this.per, usuario);
                            break;
                        case 7:
                            this.vehi.setArticulo(art);
                            exitoso = pGenerico.insertar_actualizar(this.vehi, usuario);
                            break;
                    }
                }
                if (exitoso) {
                    MensajesUI.infoGenerico();
                } else if (!exitoso && art != null && accion.equals("I")) {
                    pGenerico.eliminar(art, usuario);
                    MensajesUI.errorGenerico();
                } else {
                    MensajesUI.errorGenerico();
                }
                init();
                actualizarGenerico();
            } catch (IOException e) {
                System.out.println("ControladorArticulo.agregar_actualizar: " + e);
            }
        }
    }

    //ELIMINAR
    public void eliminar() {
        boolean exitoso = false;
        switch (articulo.getCategoria().getSecuencia().intValue()) {
            case 1:
                exitoso = pGenerico.eliminar(this.equiCom, usuario);
                break;
            case 2:
                exitoso = pGenerico.eliminar(this.mbsMje, usuario);
                break;
            case 3:
                exitoso = pGenerico.eliminar(this.mbsOfc, usuario);
                break;
            case 4:
                exitoso = pGenerico.eliminar(this.lab, usuario);
                break;
            case 5:
                exitoso = pGenerico.eliminar(this.top, usuario);
                break;
            case 6:
                exitoso = pGenerico.eliminar(this.per, usuario);
                break;
            case 7:
                exitoso = pGenerico.eliminar(this.vehi, usuario);
                break;
        }
        if (exitoso) {
            exitoso = pGenerico.eliminar(this.articulo, usuario);
        }
        if (exitoso) {
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

    public void alistarFactura() {
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Pragma", "no-cache");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Expires", "0");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Expires", "Mon, 8 Aug 1980 10:00:00 GMT");
        try {
            String nomArchivo = "Factura" + articulo.getFactura() + ".pdf";
            ruta = rutaGeneral + nomArchivo;
            FileUtils.writeByteArrayToFile(new File(ruta), articulo.getFacturaDigital());
            verFactura = "/SGI-war/sgi/recursos/verFactura/" + nomArchivo;
        } catch (Exception e) {
            verFactura = null;
        }
        PrimefacesContextUI.actualizar("principalForm:verFactura");
        PrimefacesContextUI.ejecutar("PF('verFactura').show();");
    }

    public void eliminarArchivo() {
        File file = new File(ruta);
        file.delete();
    }

    public void reiniciarStreamedContent() {
        // verFactura = null;
    }

    //GETTER AND SETTER
    public List<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public List<Categoria> getListacaCategorias() {
        return listacaCategorias;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public ArtEquiCom getEquiCom() {
        return equiCom;
    }

    public void setEquiCom(ArtEquiCom equiCom) {
        this.equiCom = equiCom;
    }

    public ArtMbsMje getMbsMje() {
        return mbsMje;
    }

    public void setMbsMje(ArtMbsMje mbsMje) {
        this.mbsMje = mbsMje;
    }

    public ArtMbsOfc getMbsOfc() {
        return mbsOfc;
    }

    public void setMbsOfc(ArtMbsOfc mbsOfc) {
        this.mbsOfc = mbsOfc;
    }

    public ArtLab getLab() {
        return lab;
    }

    public void setLab(ArtLab lab) {
        this.lab = lab;
    }

    public ArtPer getPer() {
        return per;
    }

    public void setPer(ArtPer per) {
        this.per = per;
    }

    public ArtTop getTop() {
        return top;
    }

    public void setTop(ArtTop top) {
        this.top = top;
    }

    public ArtVehi getVehi() {
        return vehi;
    }

    public void setVehi(ArtVehi vehi) {
        this.vehi = vehi;
    }

    public List<String> getClasificaciones() {
        return clasificaciones;
    }

    public List<String> getMarcas() {
        return marcas;
    }

    public UploadedFile getFactura() {
        return factura;
    }

    public void setFactura(UploadedFile factura) {
        this.factura = factura;
    }

    public String getAccion() {
        return accion;
    }

    public String getVerFactura() {
        return verFactura;
    }

    public void setVerFactura(String verFactura) {
        this.verFactura = verFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

}
