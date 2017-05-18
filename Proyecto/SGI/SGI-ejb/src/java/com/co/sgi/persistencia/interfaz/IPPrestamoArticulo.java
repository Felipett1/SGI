package com.co.sgi.persistencia.interfaz;

import com.co.sgi.Entidad.Articulo;
import com.co.sgi.Entidad.PrestamoArticulo;
import com.co.sgi.Entidad.Responsable;
import java.util.List;

/**
 *
 * @author Felipe Triviño
 */
public interface IPPrestamoArticulo {

    public List<Responsable> consultarResponsablesActivos();

    public List<Articulo> consultarArticulosDisponibles(List<String> excluidos);

    public List<PrestamoArticulo> obtenerPrestamoArticulo(Long secuencia);
    
}
