package com.co.sgi.persistencia.implementacion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.co.sgi.persistencia.interfaz.IPArticulo;
import javax.persistence.Query;

/**
 *
 * @author Felipe Triviño
 */
@Stateless
public class PArticulo implements IPArticulo {

    @PersistenceContext(unitName = "UP-SGI")
    private EntityManager em;

    @Override
    public Object consultarCategoria(Long secuencia, int categoria) {
        String query = "SELECT T FROM ";
        switch (categoria) {
            case 1:
                query += "ArtEquiCom";
                break;
            case 2:
                query += "ArtMbsMje";
                break;
            case 3:
                query += "ArtMbsOfc";
                break;
            case 4:
                query += "ArtLab";
                break;
            case 5:
                query += "ArtTop";
                break;
            case 6:
                query += "ArtPer";
                break;
            case 7:
                query += "ArtVehi";
                break;
        }
        query += " T WHERE T.articulo.secuencia = " + secuencia;
        Query q = em.createQuery(query);
        return q.getSingleResult();
    }

}
