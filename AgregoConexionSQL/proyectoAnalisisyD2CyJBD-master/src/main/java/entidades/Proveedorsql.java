/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MicroSistemas
 */
@Entity
@Table(name = "proveedorsql")
@NamedQueries({
    @NamedQuery(name = "Proveedorsql.findAll", query = "SELECT p FROM Proveedorsql p"),
    @NamedQuery(name = "Proveedorsql.findByIdProveedorsql", query = "SELECT p FROM Proveedorsql p WHERE p.idProveedorsql = :idProveedorsql"),
    @NamedQuery(name = "Proveedorsql.findByNombre", query = "SELECT p FROM Proveedorsql p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Proveedorsql.findByDireccion", query = "SELECT p FROM Proveedorsql p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proveedorsql.findByTelefono", query = "SELECT p FROM Proveedorsql p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Proveedorsql.findByObservacion", query = "SELECT p FROM Proveedorsql p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "Proveedorsql.findByTipo", query = "SELECT p FROM Proveedorsql p WHERE p.tipo = :tipo")})
public class Proveedorsql implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProveedorsql")
    private Integer idProveedorsql;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorsqlidProveedorsql")
    private Collection<Bodegasql> bodegasqlCollection;

    public Proveedorsql() {
    }

    public Proveedorsql(Integer idProveedorsql) {
        this.idProveedorsql = idProveedorsql;
    }

    public Integer getIdProveedorsql() {
        return idProveedorsql;
    }

    public void setIdProveedorsql(Integer idProveedorsql) {
        this.idProveedorsql = idProveedorsql;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Collection<Bodegasql> getBodegasqlCollection() {
        return bodegasqlCollection;
    }

    public void setBodegasqlCollection(Collection<Bodegasql> bodegasqlCollection) {
        this.bodegasqlCollection = bodegasqlCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedorsql != null ? idProveedorsql.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedorsql)) {
            return false;
        }
        Proveedorsql other = (Proveedorsql) object;
        if ((this.idProveedorsql == null && other.idProveedorsql != null) || (this.idProveedorsql != null && !this.idProveedorsql.equals(other.idProveedorsql))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.depositodeagua.Proveedorsql[ idProveedorsql=" + idProveedorsql + " ]";
    }
    
}
