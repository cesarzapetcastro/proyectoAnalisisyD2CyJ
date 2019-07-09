/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.depositodeagua;

/**
 *
 * @author Jorge
 */
public abstract class Producto {
     private String nombre;
    private String descripcion;
     private String marca;
     
      public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }
    
     public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public Producto(String nombre, String descripcion, String marca) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setMarca(marca);

    }
       public abstract String getInforme();


    
   
}

