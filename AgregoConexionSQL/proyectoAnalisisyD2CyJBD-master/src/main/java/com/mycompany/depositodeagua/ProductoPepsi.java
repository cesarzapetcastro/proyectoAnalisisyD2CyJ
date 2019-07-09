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
public class ProductoPepsi extends Producto{
    
    public ProductoPepsi(String nombre, String descripcion, String marca) {
        super(nombre, descripcion, marca);
    }

    @Override
    public String getInforme() {
        return("Se Creo un producto pepsi"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
