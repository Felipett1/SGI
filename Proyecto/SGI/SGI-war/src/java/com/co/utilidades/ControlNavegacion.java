package com.co.utilidades;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.text.WordUtils;

@ManagedBean
@SessionScoped
public class ControlNavegacion implements Serializable {

    private String urlMenuNavegation;
    private String urlNavegationDialog;
    private String urlNavegationDialogE;
    private String urlContenidoDinamico;
    private String tituloDialogo;
    private String controladorAnterior;

    @PostConstruct
    public void init() {
        urlMenuNavegation = "";
    }

    public String getUrlNavegation() {
        return urlMenuNavegation;
    }

    public String getUrlMenuNavegation() {
        return urlMenuNavegation;
    }

    public void setUrlMenuNavegation(String urlMenuNavegation) {
        this.urlMenuNavegation = urlMenuNavegation;
    }

    public void setUrlNavegation(String urlNavegation) {
        this.urlMenuNavegation = urlNavegation;
    }

    public String getUrlNavegationDialog() {
        return urlNavegationDialog;
    }

    public void setUrlNavegationDialog(String urlNavegationDialog) {
        this.urlNavegationDialog = urlNavegationDialog;
    }

    public String getTituloDialogo() {
        return tituloDialogo;
    }

    public void setTituloDialogo(String tituloDialogo) {
        this.tituloDialogo = tituloDialogo;
    }

    public String getUrlNavegationDialogE() {
        return urlNavegationDialogE;
    }

    public void setUrlNavegationDialogE(String urlNavegationDialogE) {
        this.urlNavegationDialogE = urlNavegationDialogE;
    }

    public String getUrlContenidoDinamico() {
        return urlContenidoDinamico;
    }

    public void setUrlContenidoDinamico(String urlContenidoDinamico) {
        this.urlContenidoDinamico = urlContenidoDinamico;
    }

    public void pantallaDinamica(String url) throws Exception {
        try {
            if (this.controladorAnterior != null) {
                FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(controladorAnterior);
            }
            this.urlMenuNavegation = url;
            this.controladorAnterior = "controlador" + obtenerNombre(url, false);
            PrimefacesContextUI.actualizar("principalForm:contenido");
        } catch (Exception e) {
            System.out.println("Error pantallaDinamica: " + e.getMessage());
        }
    }

    public void dialogoDinamico(String url) throws Exception {
        try {
            this.urlNavegationDialog = url;
            this.tituloDialogo = obtenerNombre(url, true);
            PrimefacesContextUI.actualizar("principalForm:dialogo");
            PrimefacesContextUI.ejecutar("PF('dialogo').show()");
        } catch (Exception e) {
            System.out.println("Error dialogoDinamico: " + e.getMessage());
        }
    }

    public void dialogoEDinamico(String url) throws Exception {
        try {
            this.urlNavegationDialogE = url;
            PrimefacesContextUI.actualizar("principalForm:dialogoE");
            PrimefacesContextUI.ejecutar("PF('dialogoE').show()");
        } catch (Exception e) {
            System.out.println("Error dialogoEDinamico: " + e.getMessage());
        }
    }

    public void formularioArticuloDinamico(int categoria) throws Exception {
        try {
            String url = "/administracion/articulo/CRUD/";
            switch (categoria) {
                case 1:
                    url = url + "equi-com";
                    break;
                case 2:
                    url = url + "mbs-mje";
                    break;
                case 3:
                    url = url + "mbs-ofc";
                    break;
                case 4:
                    url = url + "lab";
                    break;
                case 5:
                    url = url + "top";
                    break;
                case 6:
                    url = url + "per";
                    break;
                case 7:
                    url = url + "vehi";
                    break;
                default:
                    url = "";
                    break;
            }
            if (categoria != 0) {
                url = url + ".xhtml";
            }
            this.urlContenidoDinamico = url;
            PrimefacesContextUI.actualizar("principalForm:articulo");
        } catch (Exception e) {
            System.out.println("Error dialogoEDinamico: " + e.getMessage());
        }
    }

    public String obtenerNombre(String url, boolean separar) {
        String palabra = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        if (palabra.contains("_")) {
            String[] palabras = palabra.split("_");
            if (separar) {
                return WordUtils.capitalize(palabras[0]) + " " + palabras[1];
            } else {
                return WordUtils.capitalize(palabras[0]) + palabras[1];
            }
        } else {
            return WordUtils.capitalize(palabra);
        }
    }
}
