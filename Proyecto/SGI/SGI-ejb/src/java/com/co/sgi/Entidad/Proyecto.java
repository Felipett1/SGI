package com.co.sgi.Entidad;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Felipe Triviño
 */
@Entity
@Table(name = "proyecto")
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Long secuencia;
    @Size(max = 50)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "empresa", referencedColumnName = "secuencia")
    @ManyToOne
    private Empresa empresa;
    @JoinColumn(name = "\"entidadContratante\"", referencedColumnName = "secuencia")
    @ManyToOne
    private EntidadContratante entidadContratante;
    @Size(max = 1000)
    @Column(name = "\"objetoContrato\"")
    private String objetoContrato;
    @Column(name = "\"fechaInicial\"")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "\"fechaFinal\"")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "estado")
    private boolean estado;

    public Proyecto() {
        this.empresa = new Empresa();
        this.entidadContratante = new EntidadContratante();
    }

    public Proyecto(Long secuencia) {
        this.secuencia = secuencia;
    }

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetoContrato() {
        return objetoContrato;
    }

    public void setObjetoContrato(String objetoContrato) {
        this.objetoContrato = objetoContrato;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EntidadContratante getEntidadContratante() {
        return entidadContratante;
    }

    public void setEntidadContratante(EntidadContratante entidadContratante) {
        this.entidadContratante = entidadContratante;
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
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sgi.Entidad.Proyecto[ secuencia=" + secuencia + " ]";
    }

}
