package com.co.sgi.persistencia.implementacion;

import com.co.sgi.Entidad.Articulo;
import com.co.sgi.Entidad.Auditoria;
import com.co.sgi.Entidad.Prestamo;
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
    private byte[] archivoAnterior, archivoNuevo;

    //Insertar o actualizar
    @Override
    public boolean insertar_actualizar(Object entity, String usuario) {
        try {
            this.usuario = usuario;
            informacionAuditoria(entity, false);
            em.merge(entity);
            insertarAuditoria();
            return true;
        } catch (Exception ex) {
            System.out.println("Error PGenerico.insertar_actualizar: " + ex);
            return false;
        }
    }

    @Override
    public Object insertar_actualizarObjeto(Object entity, String usuario) {
        try {
            this.usuario = usuario;
            informacionAuditoria(entity, false);
            Object obj = em.merge(entity);
            insertarAuditoria();
            return obj;
        } catch (Exception ex) {
            System.out.println("Error PGenerico.insertar_actualizar: " + ex);
            return null;
        }
    }

    //Eliminar
    @Override
    public boolean eliminar(Object entity, String usuario) {
        try {
            this.usuario = usuario;
            entity = informacionAuditoria(entity, true);
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

    @Override
    public Object consultar(Object entity) {
        try {
            Object llave = validarLlave(entity);
            return em.find(entity.getClass(), llave);
        } catch (Exception e) {
            System.out.println("Error PGenerico.consultar solo: " + e);
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
                if (!f.getName().startsWith("_") && !f.getName().contains("serialVersionUID")) {
                    f.setAccessible(true);
                    Object value = f.get(o);
                    if (value != null) {
                        datos = datos + f.getName() + ": " + value.toString() + " - ";
                    }
                    j++;
                }
            }
            datos = datos.substring(0, datos.lastIndexOf("-"));
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

    private Object informacionAuditoria(Object entity, boolean eliminar) {
        String antes = null, despues;
        Object llave = validarLlave(entity);
        Object obj = null;
        tabla = entity.getClass().getSimpleName();
        if (llave != null) {
            obj = em.find(entity.getClass(), llave);
            antes = obtenerDetalle(obj);
            if (!eliminar) {
                accion = "UPDATE";
            } else {
                accion = "DELETE";
            }
        } else {
            accion = "INSERT";
        }
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

        if (tabla.equals("Articulo")) {
            if (accion.equals("INSERT")) {
                archivoAnterior = null;
                archivoNuevo = ((Articulo) entity).getFacturaDigital();
            } else if (accion.equals("UPDATE")) {
                archivoAnterior = ((Articulo) obj).getFacturaDigital();
                archivoNuevo = ((Articulo) entity).getFacturaDigital();
                if (archivoAnterior == archivoNuevo) {
                    archivoAnterior = null;
                    archivoNuevo = null;
                }
            } else {
                archivoAnterior = ((Articulo) obj).getFacturaDigital();
                archivoNuevo = null;
            }
        } else if (tabla.equals("Prestamo")) {
            if (accion.equals("INSERT")) {
                archivoAnterior = null;
                archivoNuevo = ((Prestamo) entity).getSoporteEntregado();
            } else if (accion.equals("UPDATE")) {
                archivoAnterior = ((Prestamo) obj).getSoporteEntregado();
                archivoNuevo = ((Prestamo) entity).getSoporteEntregado();
                if (archivoAnterior == archivoNuevo) {
                    archivoAnterior = null;
                    archivoNuevo = null;
                }
            } else {
                archivoAnterior = ((Prestamo) obj).getSoporteEntregado();
                archivoNuevo = null;
            }
        } else {
            archivoAnterior = null;
            archivoNuevo = null;
        }
        return obj;
    }

    private boolean insertarAuditoria() {
        Auditoria a = new Auditoria(null, new Date(), usuario, accion, tabla, detalle, archivoAnterior, archivoNuevo);
        em.merge(a);
        return true;
    }
}
