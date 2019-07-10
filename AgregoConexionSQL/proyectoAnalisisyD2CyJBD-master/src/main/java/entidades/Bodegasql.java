/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

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

/**
 *
 * @author MicroSistemas
 */
@Entity
@Table(name = "bodegasql")
@NamedQueries({
    @NamedQuery(name = "Bodegasql.findAll", query = "SELECT b FROM Bodegasql b"),
    @NamedQuery(name = "Bodegasql.findByIdBodega", query = "SELECT b FROM Bodegasql b WHERE b.idBodega = :idBodega"),
    @NamedQuery(name = "Bodegasql.findByCantidad", query = "SELECT b FROM Bodegasql b WHERE b.cantidad = :cantidad"),
    @NamedQuery(name = "Bodegasql.findByFechaDeCaducidad", query = "SELECT b FROM Bodegasql b WHERE b.fechaDeCaducidad = :fechaDeCaducidad"),
    @NamedQuery(name = "Bodegasql.findByFechaDeEntrada", query = "SELECT b FROM Bodegasql b WHERE b.fechaDeEntrada = :fechaDeEntrada")})
public class Bodegasql implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBodega")
    private Integer idBodega;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fecha_de_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaDeCaducidad;
    @Column(name = "fecha_de-entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaDeEntrada;
    @JoinColumn(name = "Productosql_idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Productosql productosqlidProducto;
    @JoinColumn(name = "Proveedorsql_idProveedorsql", referencedColumnName = "idProveedorsql")
    @ManyToOne(optional = false)
    private Proveedorsql proveedorsqlidProveedorsql;

    public Bodegasql() {
    }

    public Bodegasql(Integer idBodega) {
        this.idBodega = idBodega;
    }

    public Integer getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Integer idBodega) {
        this.idBodega = idBodega;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaDeCaducidad() {
        return fechaDeCaducidad;
    }

    public void setFechaDeCaducidad(Date fechaDeCaducidad) {
        this.fechaDeCaducidad = fechaDeCaducidad;
    }

    public Date getFechaDeEntrada() {
        return fechaDeEntrada;
    }

    public void setFechaDeEntrada(Date fechaDeEntrada) {
        this.fechaDeEntrada = fechaDeEntrada;
    }

    public Productosql getProductosqlidProducto() {
        return productosqlidProducto;
    }

    public void setProductosqlidProducto(Productosql productosqlidProducto) {
        this.productosqlidProducto = productosqlidProducto;
    }

    public Proveedorsql getProveedorsqlidProveedorsql() {
        return proveedorsqlidProveedorsql;
    }

    public void setProveedorsqlidProveedorsql(Proveedorsql proveedorsqlidProveedorsql) {
        this.proveedorsqlidProveedorsql = proveedorsqlidProveedorsql;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBodega != null ? idBodega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodegasql)) {
            return false;
        }
        Bodegasql other = (Bodegasql) object;
        if ((this.idBodega == null && other.idBodega != null) || (this.idBodega != null && !this.idBodega.equals(other.idBodega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.depositodeagua.Bodegasql[ idBodega=" + idBodega + " ]";
    }
    
}
