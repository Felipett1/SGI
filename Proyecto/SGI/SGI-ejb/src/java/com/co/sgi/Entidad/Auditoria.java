package com.co.sgi.Entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Felipe Triviño
 */
@Entity
@Table(name = "auditoria")
@NamedQueries({
    @NamedQuery(name = "Auditoria.findAll", query = "SELECT a FROM Auditoria a")})
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Long secuencia;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 50)
    @Column(name = "accion")
    private String accion;
    @Size(max = 50)
    @Column(name = "tabla")
    private String tabla;
    @Size(max = 1000)
    @Column(name = "detalle")
    private String detalle;
    @Lob
    @Column(name = "\"archivoAnterior\"")
    private byte[] archivoAnterior;
    @Lob
    @Column(name = "\"archivoNuevo\"")
    private byte[] archivoNuevo;

    public Auditoria() {
    }

    public Auditoria(Long secuencia) {
        this.secuencia = secuencia;
    }

    public Auditoria(Long secuencia, Date fecha, String usuario, String accion, String tabla, String detalle, byte[] archivoAnterior, byte[] archivoNuevo) {
        this.secuencia = secuencia;
        this.fecha = fecha;
        this.usuario = usuario;
        this.accion = accion;
        this.tabla = tabla;
        this.detalle = detalle;
        this.archivoAnterior = archivoAnterior;
        this.archivoNuevo = archivoNuevo;
    }

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public byte[] getArchivoAnterior() {
        return archivoAnterior;
    }

    public void setArchivoAnterior(byte[] archivoAnterior) {
        this.archivoAnterior = archivoAnterior;
    }

    public byte[] getArchivoNuevo() {
        return archivoNuevo;
    }

    public void setArchivoNuevo(byte[] archivoNuevo) {
        this.archivoNuevo = archivoNuevo;
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
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sgi.Entidad.Auditoria[ secuencia=" + secuencia + " ]";
    }

}
