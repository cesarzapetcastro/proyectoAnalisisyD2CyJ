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
public class ProductoCocaCola extends Producto {
    
    public ProductoCocaCola(String nombre, String descripcion, String marca) {
        super(nombre, descripcion, marca);
    }

    @Override
    public String getInforme() {
        return("Se creo producto coca cola"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
