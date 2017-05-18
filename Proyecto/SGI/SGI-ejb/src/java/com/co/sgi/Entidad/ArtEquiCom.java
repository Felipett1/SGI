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
import javax.validation.constraints.Size;

/**
 *
 * @author Felipe Triviño
 */
@Entity
@Table(name = "\"art-equi-com\"")
@NamedQueries({
    @NamedQuery(name = "ArtEquiCom.findAll", query = "SELECT e FROM ArtEquiCom e")})
public class ArtEquiCom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Long secuencia;
    @Size(max = 50)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 50)
    @Column(name = "serie")
    private String serie;
    @Size(max = 50)
    @Column(name = "\"discoDuro\"")
    private String discoDuro;
    @Size(max = 50)
    @Column(name = "memoria")
    private String memoria;
    @Size(max = 50)
    @Column(name = "procesador")
    private String procesador;
    @Column(name = "\"kitErgonomico\"")
    private Boolean kitErgonomico;
    @Size(max = 100)
    @Column(name = "proveedor")
    private String proveedor;
    @Size(max = 1000)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 100)
    @Column(name = "\"tarjetaVideo\"")
    private String tarjetaVideo;
    @Size(max = 100)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Size(max = 100)
    @Column(name = "marca")
    private String marca;
    @JoinColumn(name = "articulo", referencedColumnName = "secuencia")
    @ManyToOne
    private Articulo articulo;

    public ArtEquiCom() {
    }

    public ArtEquiCom(Long secuencia) {
        this.secuencia = secuencia;
    }

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(String discoDuro) {
        this.discoDuro = discoDuro;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Boolean getKitErgonomico() {
        return kitErgonomico;
    }

    public void setKitErgonomico(Boolean kitErgonomico) {
        this.kitErgonomico = kitErgonomico;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTarjetaVideo() {
        return tarjetaVideo;
    }

    public void setTarjetaVideo(String tarjetaVideo) {
        this.tarjetaVideo = tarjetaVideo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
        if (!(object instanceof ArtEquiCom)) {
            return false;
        }
        ArtEquiCom other = (ArtEquiCom) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sgi.Entidad.EquiCom[ secuencia=" + secuencia + " ]";
    }

}
