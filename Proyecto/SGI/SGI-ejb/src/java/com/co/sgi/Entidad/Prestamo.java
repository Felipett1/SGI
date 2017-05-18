package com.co.sgi.Entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Felipe Triviño
 */
@Entity
@Table(name = "prestamo")
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Long secuencia;
    @Size(max = 500)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "responsable", referencedColumnName = "secuencia", nullable = false)
    @ManyToOne(optional = false)
    private Responsable responsable;
    @Lob
    @Column(name = "\"soporteEntregado\"")
    private byte[] soporteEntregado;
    @Lob
    @Column(name = "\"soporteDevolucion\"")
    private byte[] soporteDevolucion;

    public Prestamo() {
    }

    public Prestamo(Long secuencia) {
        this.secuencia = secuencia;
    }

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public byte[] getSoporteEntregado() {
        return soporteEntregado;
    }

    public void setSoporteEntregado(byte[] soporteEntregado) {
        this.soporteEntregado = soporteEntregado;
    }

    public byte[] getSoporteDevolucion() {
        return soporteDevolucion;
    }

    public void setSoporteDevolucion(byte[] soporteDevolucion) {
        this.soporteDevolucion = soporteDevolucion;
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
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sgi.Entidad.Prestamo[ secuencia=" + secuencia + " ]";
    }

}
