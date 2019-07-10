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
@Table(name = "productosql")
@NamedQueries({
    @NamedQuery(name = "Productosql.findAll", query = "SELECT p FROM Productosql p"),
    @NamedQuery(name = "Productosql.findByIdProducto", query = "SELECT p FROM Productosql p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Productosql.findByNombre", query = "SELECT p FROM Productosql p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Productosql.findByDescipcion", query = "SELECT p FROM Productosql p WHERE p.descipcion = :descipcion"),
    @NamedQuery(name = "Productosql.findByMarca", query = "SELECT p FROM Productosql p WHERE p.marca = :marca")})
public class Productosql implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descipcion")
    private String descipcion;
    @Column(name = "marca")
    private String marca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosqlidProducto")
    private Collection<Bodegasql> bodegasqlCollection;

    public Productosql() {
    }

    public Productosql(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productosql)) {
            return false;
        }
        Productosql other = (Productosql) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.depositodeagua.Productosql[ idProducto=" + idProducto + " ]";
    }
    
}
