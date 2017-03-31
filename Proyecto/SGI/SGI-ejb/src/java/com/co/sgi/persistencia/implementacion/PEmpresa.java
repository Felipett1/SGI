package com.co.sgi.persistencia.implementacion;

import com.co.sgi.persistencia.interfaz.IPEmpresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Felipe Triviño
 */
@Stateless
public class PEmpresa implements IPEmpresa {

    @PersistenceContext(unitName = "UP-SGI")
    private EntityManager em;


}
