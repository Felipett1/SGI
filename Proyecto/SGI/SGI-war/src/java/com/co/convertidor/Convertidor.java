package com.co.convertidor;

import com.co.sgi.persistencia.interfaz.IPGenerico;
import java.lang.reflect.Field;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@RequestScoped
public class Convertidor implements Converter {

    @EJB
    IPGenerico pGenerico;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equalsIgnoreCase("Seleccione") && value.trim().length() > 0) {
            return obtenerEntidad(value);
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return object.toString();
        } else {
            return null;
        }
    }

    public Object obtenerEntidad(String valor) {
        try {
            String clase = valor.substring(0, valor.indexOf("["));
            Long secuencia = Long.valueOf(valor.substring(valor.indexOf("=") + 1, valor.lastIndexOf(" ")));
            Class c = Class.forName(clase);
            Object o = c.newInstance();
            Field f = o.getClass().getDeclaredField("secuencia");
            f.setAccessible(true);
            f.set(o, secuencia);
            return pGenerico.consultar(o);
        } catch (Exception e) {
            System.out.println("Error Convertidor.obtenerEntidad: " + e);
            return null;
        }
    }
}
