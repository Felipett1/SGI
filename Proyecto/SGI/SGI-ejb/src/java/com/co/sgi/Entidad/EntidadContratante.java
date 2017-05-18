package com.co.sgi.Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Felipe Triviño
 */
@Entity
@Table(name = "\"entidadContratante\"")
@NamedQueries({
    @NamedQuery(name = "EntidadContratante.findAll", query = "SELECT e FROM EntidadContratante e")})
public class EntidadContratante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Long secuencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nit")
    private long nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;

    public EntidadContratante() {
    }

    public EntidadContratante(Long secuencia) {
        this.secuencia = secuencia;
    }

    public EntidadContratante(Long secuencia, long nit, String nombre) {
        this.secuencia = secuencia;
        this.nit = nit;
        this.nombre = nombre;
    }

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuencia != null ? secuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadContratante)) {
            return false;
        }
        EntidadContratante other = (EntidadContratante) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sgi.Entidad.EntidadContratante[ secuencia=" + secuencia + " ]";
    }
    
}
