package com.co.sgi.persistencia.implementacion;

import com.co.sgi.Entidad.Articulo;
import com.co.sgi.Entidad.PrestamoArticulo;
import com.co.sgi.Entidad.Responsable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;
import com.co.sgi.persistencia.interfaz.IPPrestamoArticulo;

/**
 *
 * @author Felipe Triviño
 */
@Stateless
public class PPrestamoArticulo implements IPPrestamoArticulo {

    @PersistenceContext(unitName = "UP-SGI")
    private EntityManager em;

    @Override
    public List<Responsable> consultarResponsablesActivos() {
        Query q = em.createQuery("SELECT R FROM Responsable R WHERE R.estado = true");
        return q.getResultList();
    }

    @Override
    public List<Articulo> consultarArticulosDisponibles(List<String> excluidos) {
        String where = quitarSeleccionados(excluidos);
        Query q = em.createQuery("SELECT A FROM Articulo A " + where);
        return q.getResultList();
    }

    public String quitarSeleccionados(List<String> excluidos) {
        String where = "WHERE A.estado = true AND A.prestado = false";
        if (excluidos != null && excluidos.size() > 0) {
            where += " AND A.secuencia NOT IN (";
            for (String e : excluidos) {
                where += e + ",";
            }
            where = where.substring(0, (where.length() - 1));
            where += ")";
        }
        return where;
    }

    public List<PrestamoArticulo> obtenerPrestamoArticulo(Long secuencia) {
        if (secuencia != null) {
            Query q = em.createQuery("SELECT PA FROM PrestamoArticulo PA WHERE PA.prestamo.secuencia = " + secuencia);
            return q.getResultList();
        } else {
            return null;
        }
    }
}
