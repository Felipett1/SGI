package com.co.controlador;

import com.co.reporte.IniciarReporte;
import com.co.sgi.Entidad.Articulo;
import com.co.sgi.Entidad.Prestamo;
import com.co.sgi.Entidad.PrestamoArticulo;
import com.co.sgi.Entidad.Responsable;
import com.co.sgi.persistencia.interfaz.IPGenerico;
import com.co.sgi.persistencia.interfaz.IPPrestamoArticulo;
import com.co.utilidades.MensajesUI;
import com.co.utilidades.PrimefacesContextUI;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Felipe Triviño
 */
@ManagedBean
@ViewScoped
public class ControladorPrestamoArticulo implements Serializable {

    @EJB
    private IPGenerico pGenerico;
    @EJB
    private IPPrestamoArticulo pPrestamoArticulo;
    private List<Prestamo> listaPrestamos;
    private Prestamo prestamo;
    private List<Responsable> listaResponsables;
    private Responsable responsable;
    private List<Articulo> articulosDisponibles;
    private List<Articulo> articulosSeleccionados;
    private Articulo articulo;
    private String verActa, ruta, estadoSoporte, rutaGeneral;
    private final String usuario = "Felipe";
    private static final String TABLA = "Prestamo", NOMBRE_REPORTE = "actaEntrega";
    private List<String> excluidos;
    private IniciarReporte iniciarReporte;
    private UploadedFile soporteEntrega;

    /**
     * Creates a new instance of ControladorEmpresa
     */
    public ControladorPrestamoArticulo() {
        //System.out.println(this.getClass().getSimpleName());
    }

    @PostConstruct
    public void init() {
        listaPrestamos = (List<Prestamo>) (Object) pGenerico.consultar(TABLA);
        FacesContext x = FacesContext.getCurrentInstance();
        HttpSession ses = (HttpSession) x.getExternalContext().getSession(false);
        iniciarReporte = ((IniciarReporte) x.getApplication().evaluateExpressionGet(x, "#{iniciarReporte}", IniciarReporte.class));
        String r = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        rutaGeneral = r.substring(0, r.indexOf("WEB-INF")) + "recursos/verFactura/";
    }

    public void iniciarLovs() {
        listaResponsables = pPrestamoArticulo.consultarResponsablesActivos();
        articulosDisponibles = pPrestamoArticulo.consultarArticulosDisponibles(excluidos);
    }

    public void seleccionarResponsable() {
        prestamo.setResponsable(responsable);
    }

    public void adicionarArticulo() {
        if (articulo != null) {
            articulosSeleccionados.add(articulo);
            excluidos.add(articulo.getSecuencia().toString());
            iniciarLovs();
        } else {
            MensajesUI.error("Debe seleccionar un articulo.");
        }
    }

    public void eliminarArticulo(Articulo a) {
        articulosSeleccionados.remove(a);
        excluidos.remove(a.getSecuencia().toString());
        iniciarLovs();
    }

    //AGREGAR
    public void limpiar() {
        prestamo = new Prestamo();
        articulosSeleccionados = new ArrayList<>();
        excluidos = new ArrayList<>();
        iniciarLovs();
    }

    //ACTUALIZAR
    public void seleccionarActualizar_Eliminar(Prestamo p) {
        prestamo = p;
        articulosSeleccionados = new ArrayList<>();
        //excluidos = new ArrayList<>();
        List<PrestamoArticulo> lpa = pPrestamoArticulo.obtenerPrestamoArticulo(prestamo.getSecuencia());
        for (PrestamoArticulo pa : lpa) {
            articulosSeleccionados.add(pa.getArticulo());
            //excluidos.add(pa.getArticulo().getSecuencia().toString());
        }
        iniciarLovs();
        if (prestamo.getSoporteEntregado() != null) {
            //Existe
            estadoSoporte = "E";
        } else {
            //No existe
            estadoSoporte = "NE";
        }
    }

    public void agregar_actualizar() {
        if (prestamo.getResponsable() != null) {
            if (articulosSeleccionados != null && articulosSeleccionados.size() > 0) {
                prestamo.setEstado(true);
                Prestamo p = (Prestamo) pGenerico.insertar_actualizarObjeto(prestamo, usuario);
                if (p != null) {
                    if (prestamo.getSecuencia() != null) {
                        MensajesUI.infoGenerico();
                    } else {
                        boolean estado = true;
                        for (Articulo a : articulosSeleccionados) {
                            a.setPrestado(true);
                            if (!pGenerico.insertar_actualizar(new PrestamoArticulo(p, a), usuario)) {
                                estado = false;
                                break;
                            }
                            pGenerico.insertar_actualizar(a, usuario);
                        }
                        if (estado) {
                            MensajesUI.infoGenerico();
                        } else {
                            MensajesUI.errorGenerico();
                        }
                    }
                } else {
                    MensajesUI.errorGenerico();
                }
                init();
                actualizarGenerico();
                PrimefacesContextUI.ejecutar("PF('dialogo').hide()");
            } else {
                MensajesUI.error("Se debe agregar al menos un articulo.");
            }
        } else {
            MensajesUI.error("Es necesario relacionar un responsable.");
        }
    }

    //ELIMINAR
    public void eliminar() {
        boolean estado = true;
        List<PrestamoArticulo> pa = pPrestamoArticulo.obtenerPrestamoArticulo(prestamo.getSecuencia());
        for (PrestamoArticulo prestamoArticulo : pa) {
            prestamoArticulo.getArticulo().setPrestado(false);
            pGenerico.insertar_actualizar(prestamoArticulo.getArticulo(), usuario);
            if (!pGenerico.eliminar(prestamoArticulo, usuario)) {
                estado = false;
                break;
            }
        }
        if (estado) {
            estado = pGenerico.eliminar(prestamo, usuario);
            if (estado) {
                MensajesUI.infoGenerico();
            } else {
                MensajesUI.errorGenerico();
            }
        } else {
            MensajesUI.errorGenerico();
        }
        init();
        actualizarGenerico();
    }

    public void actualizarGenerico() {
        PrimefacesContextUI.actualizar("principalForm:dtbDatos");
    }

    public void generarActaEntrega(Prestamo p) {
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Pragma", "no-cache");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Expires", "0");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Expires", "Mon, 8 Aug 1980 10:00:00 GMT");
        Map parametros = new HashMap();
        parametros.put("secuencia", p.getSecuencia());
        String nombreArchivo = "ActaPrestamo_" + p.getSecuencia().toString() + ".pdf";
        ruta = iniciarReporte.ejecutarReporte(NOMBRE_REPORTE, nombreArchivo, parametros);
        if (!ruta.startsWith("Error:")) {
            verActa = "/SGI-war/sgi/recursos/verReporte/" + nombreArchivo;
            PrimefacesContextUI.actualizar("principalForm:verReporte");
            PrimefacesContextUI.ejecutar("PF('verReporte').show();");
        } else {
            MensajesUI.error("Error generando el reporte.");
        }
    }

    public void eliminarArchivo() {
        File file = new File(ruta);
        file.delete();
    }

    public void cargarArchivo(FileUploadEvent event) {
        if (estadoSoporte.equals("E")) {
            //Actualizado
            estadoSoporte = "AC";
        } else {
            estadoSoporte = "AG";
        }
        this.soporteEntrega = event.getFile();
    }

    public void agregar_actualizarSoporte() {
        if (!estadoSoporte.equals("E") && !estadoSoporte.equals("NE")) {
            prestamo.setSoporteEntregado(soporteEntrega.getContents());
            agregar_actualizar();
        }
    }

    public void alistarSoporte() {
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Pragma", "no-cache");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Expires", "0");
        FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("Expires", "Mon, 8 Aug 1980 10:00:00 GMT");
        try {
            String nomArchivo = "Soporte_Prestamo_" + prestamo.getSecuencia() + ".pdf";
            ruta = rutaGeneral + nomArchivo;
            FileUtils.writeByteArrayToFile(new File(ruta), prestamo.getSoporteEntregado());
            verActa = "/SGI-war/sgi/recursos/verFactura/" + nomArchivo;
        } catch (IOException e) {
            verActa = null;
        }
        PrimefacesContextUI.actualizar("principalForm:verReporte");
        PrimefacesContextUI.ejecutar("PF('verReporte').show();");
    }

    //GETTER AND SETTER
    public List<Articulo> getArticulosSeleccionados() {
        return articulosSeleccionados;
    }

    public List<Responsable> getListaResponsables() {
        return listaResponsables;
    }

    public List<Articulo> getArticulosDisponibles() {
        return articulosDisponibles;
    }

    public List<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setListaPrestamos(List<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getVerActa() {
        return verActa;
    }

    public UploadedFile getSoporteEntrega() {
        return soporteEntrega;
    }

    public void setSoporteEntrega(UploadedFile soporteEntrega) {
        this.soporteEntrega = soporteEntrega;
    }

    public String getEstadoSoporte() {
        return estadoSoporte;
    }
}
