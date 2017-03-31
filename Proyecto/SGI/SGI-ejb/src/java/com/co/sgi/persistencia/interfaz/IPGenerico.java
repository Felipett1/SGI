package com.co.sgi.persistencia.interfaz;

import java.util.List;

/**
 *
 * @author Felipe Triviño
 */
public interface IPGenerico {
    public boolean insertar_actualizar(Object entity, String usuario);
    public boolean eliminar(Object entity);
    public List<Object> consultar(String clase);
}
