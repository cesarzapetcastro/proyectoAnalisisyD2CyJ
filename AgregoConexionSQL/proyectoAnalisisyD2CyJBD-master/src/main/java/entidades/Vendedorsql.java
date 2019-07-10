/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "vendedorsql")
@NamedQueries({
    @NamedQuery(name = "Vendedorsql.findAll", query = "SELECT v FROM Vendedorsql v"),
    @NamedQuery(name = "Vendedorsql.findById", query = "SELECT v FROM Vendedorsql v WHERE v.id = :id"),
    @NamedQuery(name = "Vendedorsql.findByNombre", query = "SELECT v FROM Vendedorsql v WHERE v.nombre = :nombre")})
public class Vendedorsql implements Serializable {

    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "telefono")
    private Integer telefono;
    @Column(name = "dieccion")
    private String dieccion;
    @Column(name = "rango")
    private String rango;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;

    public Vendedorsql() {
    }

    public Vendedorsql(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedorsql)) {
            return false;
        }
        Vendedorsql other = (Vendedorsql) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.depositodeagua.Vendedorsql[ id=" + id + " nombre = " + nombre + " ]";
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDieccion() {
        return dieccion;
    }

    public void setDieccion(String dieccion) {
        this.dieccion = dieccion;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
    
}
