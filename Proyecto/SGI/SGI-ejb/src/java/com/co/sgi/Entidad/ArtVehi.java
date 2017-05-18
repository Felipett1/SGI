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
@Table(name = "\"art-vehi\"")
@NamedQueries({
    @NamedQuery(name = "ArtVehi.findAll", query = "SELECT a FROM ArtVehi a")})
public class ArtVehi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Long secuencia;
    @Size(max = 100)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Size(max = 100)
    @Column(name = "marca")
    private String marca;
    @Size(max = 50)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 50)
    @Column(name = "color")
    private String color;
    @Size(max = 80)
    @Column(name = "chasis")
    private String chasis;
    @Size(max = 80)
    @Column(name = "motor")
    private String motor;
    @Column(name = "\"fechaExpSOAT\"")
    @Temporal(TemporalType.DATE)
    private Date fechaExpSOAT;
    @Column(name = "\"fechaVencimientoTM\"")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoTM;
    @Size(max = 10)
    @Column(name = "placa")
    private String placa;
    @Size(max = 50)
    @Column(name = "linea")
    private String linea;
    @Column(name = "cilindraje")
    private Integer cilindraje;
    @Size(max = 20)
    @Column(name = "servicio")
    private String servicio;
    @Size(max = 20)
    @Column(name = "clase")
    private String clase;
    @Size(max = 30)
    @Column(name = "carroceria")
    private String carroceria;
    @Size(max = 20)
    @Column(name = "combustible")
    private String combustible;
    @Size(max = 30)
    @Column(name = "capacidad")
    private String capacidad;
    @Size(max = 30)
    @Column(name = "reg")
    private String reg;
    @Size(max = 30)
    @Column(name = "vin")
    private String vin;
    @Size(max = 40)
    @Column(name = "serie")
    private String serie;
    @Size(max = 80)
    @Column(name = "propietario")
    private String propietario;
    @Column(name = "\"fechaMatricula\"")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;
    @Column(name = "\"fechaLicenciaTransito\"")
    @Temporal(TemporalType.DATE)
    private Date fechaLicenciaTransito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "\"tafiaSOAT\"")
    private Double tafiaSOAT;
    @Column(name = "\"primaSOAT\"")
    private Double primaSOAT;
    @Column(name = "\"contribucionFOSYGA\"")
    private Double contribucionFOSYGA;
    @Column(name = "\"tasaRUNT\"")
    private Double tasaRUNT;
    @Column(name = "\"totalSOAT\"")
    private Double totalSOAT;
    @Column(name = "at")
    private Character at;
    @Column(name = "\"fechaTecnicomecanica\"")
    @Temporal(TemporalType.DATE)
    private Date fechaTecnicomecanica;
    @Column(name = "\"numeroTarjetaOper\"")
    private Character numeroTarjetaOper;
    @Column(name = "\"fechaExpTO\"")
    @Temporal(TemporalType.DATE)
    private Date fechaExpTO;
    @Column(name = "\"fechaVencimientoTO\"")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoTO;
    @Column(name = "\"numeroPolizaCA\"")
    private Character numeroPolizaCA;
    @Column(name = "\"fechaExpPCA\"")
    @Temporal(TemporalType.DATE)
    private Date fechaExpPCA;
    @Column(name = "\"fechaVencimientoPCA\"")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoPCA;
    @Column(name = "\"numeroPolizaECA\"")
    private Character numeroPolizaECA;
    @Column(name = "\"fechaExpECA\"")
    @Temporal(TemporalType.DATE)
    private Date fechaExpECA;
    @Column(name = "\"fechaVencimientoECA\"")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoECA;
    @JoinColumn(name = "articulo", referencedColumnName = "secuencia")
    @ManyToOne
    private Articulo articulo;
    @Size(max = 1000)
    @Column(name = "observacion")
    private String observacion;

    public ArtVehi() {
    }

    public ArtVehi(Long secuencia) {
        this.secuencia = secuencia;
    }

    public Long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Date getFechaExpSOAT() {
        return fechaExpSOAT;
    }

    public void setFechaExpSOAT(Date fechaExpSOAT) {
        this.fechaExpSOAT = fechaExpSOAT;
    }

    public Date getFechaVencimientoTM() {
        return fechaVencimientoTM;
    }

    public void setFechaVencimientoTM(Date fechaVencimientoTM) {
        this.fechaVencimientoTM = fechaVencimientoTM;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Date getFechaLicenciaTransito() {
        return fechaLicenciaTransito;
    }

    public void setFechaLicenciaTransito(Date fechaLicenciaTransito) {
        this.fechaLicenciaTransito = fechaLicenciaTransito;
    }

    public Double getTafiaSOAT() {
        return tafiaSOAT;
    }

    public void setTafiaSOAT(Double tafiaSOAT) {
        this.tafiaSOAT = tafiaSOAT;
    }

    public Double getPrimaSOAT() {
        return primaSOAT;
    }

    public void setPrimaSOAT(Double primaSOAT) {
        this.primaSOAT = primaSOAT;
    }

    public Double getContribucionFOSYGA() {
        return contribucionFOSYGA;
    }

    public void setContribucionFOSYGA(Double contribucionFOSYGA) {
        this.contribucionFOSYGA = contribucionFOSYGA;
    }

    public Double getTasaRUNT() {
        return tasaRUNT;
    }

    public void setTasaRUNT(Double tasaRUNT) {
        this.tasaRUNT = tasaRUNT;
    }

    public Double getTotalSOAT() {
        return totalSOAT;
    }

    public void setTotalSOAT(Double totalSOAT) {
        this.totalSOAT = totalSOAT;
    }

    public Character getAt() {
        return at;
    }

    public void setAt(Character at) {
        this.at = at;
    }

    public Date getFechaTecnicomecanica() {
        return fechaTecnicomecanica;
    }

    public void setFechaTecnicomecanica(Date fechaTecnicomecanica) {
        this.fechaTecnicomecanica = fechaTecnicomecanica;
    }

    public Character getNumeroTarjetaOper() {
        return numeroTarjetaOper;
    }

    public void setNumeroTarjetaOper(Character numeroTarjetaOper) {
        this.numeroTarjetaOper = numeroTarjetaOper;
    }

    public Date getFechaExpTO() {
        return fechaExpTO;
    }

    public void setFechaExpTO(Date fechaExpTO) {
        this.fechaExpTO = fechaExpTO;
    }

    public Date getFechaVencimientoTO() {
        return fechaVencimientoTO;
    }

    public void setFechaVencimientoTO(Date fechaVencimientoTO) {
        this.fechaVencimientoTO = fechaVencimientoTO;
    }

    public Character getNumeroPolizaCA() {
        return numeroPolizaCA;
    }

    public void setNumeroPolizaCA(Character numeroPolizaCA) {
        this.numeroPolizaCA = numeroPolizaCA;
    }

    public Date getFechaExpPCA() {
        return fechaExpPCA;
    }

    public void setFechaExpPCA(Date fechaExpPCA) {
        this.fechaExpPCA = fechaExpPCA;
    }

    public Date getFechaVencimientoPCA() {
        return fechaVencimientoPCA;
    }

    public void setFechaVencimientoPCA(Date fechaVencimientoPCA) {
        this.fechaVencimientoPCA = fechaVencimientoPCA;
    }

    public Character getNumeroPolizaECA() {
        return numeroPolizaECA;
    }

    public void setNumeroPolizaECA(Character numeroPolizaECA) {
        this.numeroPolizaECA = numeroPolizaECA;
    }

    public Date getFechaExpECA() {
        return fechaExpECA;
    }

    public void setFechaExpECA(Date fechaExpECA) {
        this.fechaExpECA = fechaExpECA;
    }

    public Date getFechaVencimientoECA() {
        return fechaVencimientoECA;
    }

    public void setFechaVencimientoECA(Date fechaVencimientoECA) {
        this.fechaVencimientoECA = fechaVencimientoECA;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof ArtVehi)) {
            return false;
        }
        ArtVehi other = (ArtVehi) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sgi.Entidad.ArtVehi[ secuencia=" + secuencia + " ]";
    }

}
