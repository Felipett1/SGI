package com.co.sgi.persistencia.interfaz;

import java.util.List;

/**
 *
 * @author Felipe Triviño
 */
public interface IPGenerico {

    public boolean insertar_actualizar(Object entity, String usuario);

    public boolean eliminar(Object entity, String usuario);

    public List<Object> consultar(String clase);

    public Object consultar(Object entity);

    public Object insertar_actualizarObjeto(Object entity, String usuario);
}
