package com.co.sgi.Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Felipe Triviño
 */
@Entity
@Table(name = "\"prestamoArticulo\"")
@NamedQueries({
    @NamedQuery(name = "PrestamoArticulo.findAll", query = "SELECT p FROM PrestamoArticulo p")})
public class PrestamoArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Long secuencia;
    @JoinColumn(name = "prestamo", referencedColumnName = "secuencia")
    @ManyToOne
    private Prestamo prestamo;
    @JoinColumn(name = "articulo", referencedColumnName = "secuencia")
    @ManyToOne
    private Articulo articulo;

    public PrestamoArticulo() {
    }

    public PrestamoArticulo(Long secuencia) {
        this.secuencia = secuencia;
    }

    public PrestamoArticulo(Prestamo prestamo, Articulo articulo) {
        this.prestamo = prestamo;
        this.articulo = articulo;
    }

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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
        if (!(object instanceof PrestamoArticulo)) {
            return false;
        }
        PrestamoArticulo other = (PrestamoArticulo) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sgi.Entidad.PrestamoArticulo[ secuencia=" + secuencia + " ]";
    }

}
