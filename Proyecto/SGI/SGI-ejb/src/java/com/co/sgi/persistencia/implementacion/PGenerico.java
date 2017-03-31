package com.co.sgi.persistencia.implementacion;

import com.co.sgi.Entidad.Auditoria;
import com.co.sgi.persistencia.interfaz.IPGenerico;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Felipe Triviño
 */
@Stateless
public class PGenerico implements IPGenerico {

    @PersistenceContext(unitName = "UP-SGI")
    private EntityManager em;
    private String usuario, accion, tabla, detalle;

    //Insertar o actualizar
    @Override
    public boolean insertar_actualizar(Object entity, String usuario) {
        try {
            informacionAuditoria(entity, false);
            em.merge(entity);
            insertarAuditoria();
            return true;
        } catch (Exception ex) {
            System.out.println("Error PGenerico.insertar_actualizar: " + ex);
            return false;
        }
    }

    //Eliminar
    @Override
    public boolean eliminar(Object entity) {
        try {
            informacionAuditoria(entity, false);
            em.remove(entity);
            insertarAuditoria();
        } catch (Exception ex) {
            System.out.println("Error PGenerico.eliminar: " + ex);
            return false;
        }
        return true;
    }

    //Consultar todo
    @Override
    public List<Object> consultar(String clase) {
        try {
            Query query = em.createNamedQuery(clase + ".findAll");
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println("Error PGenerico.consultar: " + ex);
            return null;
        }
    }

    private String obtenerDetalle(Object o) {
        String datos = "";
        try {
            Field[] field = o.getClass().getDeclaredFields();
            int i = field.length;
            int j = 0;
            for (Field f : field) {
                f.setAccessible(true);
                Object value = f.get(o);
                if (value != null) {
                    if (j < (i - 1)) {
                        datos = datos + f.getName() + ": " + value.toString() + " - ";
                    } else {
                        datos = datos + f.getName() + ": " + value.toString();
                    }
                }
                j++;
            }
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
            System.out.println("Error PGenerico.obtenerDetalle: " + e);
        }
        return datos;
    }

    private Object validarLlave(Object o) {
        try {
            Field f = o.getClass().getDeclaredField("secuencia");
            f.setAccessible(true);
            Object value = f.get(o);
            return value;
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | NoSuchFieldException e) {
            System.out.println("Error PGenerico.validarLlave: " + e);
            return null;
        }
    }

    private void informacionAuditoria(Object entity, boolean eliminar) {
        String antes = null, despues;
        Object llave = validarLlave(entity);
        if (llave != null) {
            Object obj = em.find(entity.getClass(), llave);
            antes = obtenerDetalle(obj);
            if (!eliminar) {
                accion = "UPDATE";
            } else {
                accion = "DELETE";
            }
        } else {
            accion = "INSERT";
        }
        tabla = entity.getClass().getSimpleName();
        despues = obtenerDetalle(entity);
        if (!eliminar) {
            if (antes != null) {
                detalle = antes + " / " + despues;
            } else {
                detalle = despues;
            }
        } else {
            detalle = antes;
        }
    }

    private boolean insertarAuditoria() {
        Auditoria a = new Auditoria(null, new Date(), usuario, accion, tabla, detalle);
        em.merge(a);
        return true;
    }
}
