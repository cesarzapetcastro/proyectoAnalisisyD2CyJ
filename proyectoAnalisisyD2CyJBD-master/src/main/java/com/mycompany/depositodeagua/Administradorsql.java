/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.depositodeagua;

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
@Table(name = "administradorsql")
@NamedQueries({
    @NamedQuery(name = "Administradorsql.findAll", query = "SELECT a FROM Administradorsql a"),
    @NamedQuery(name = "Administradorsql.findById", query = "SELECT a FROM Administradorsql a WHERE a.id = :id"),
    @NamedQuery(name = "Administradorsql.findByNombre", query = "SELECT a FROM Administradorsql a WHERE a.nombre = :nombre")})
public class Administradorsql implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;

    public Administradorsql() {
    }

    public Administradorsql(Integer id) {
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
        if (!(object instanceof Administradorsql)) {
            return false;
        }
        Administradorsql other = (Administradorsql) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.depositodeagua.Administradorsql[ id=" + id + "  nombre = " + nombre + " ]";
    }
    
}
