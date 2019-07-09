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
public class ProductoFactory implements ProductoFactoryMethod{

    @Override
    public Producto CrearProducto(String nombre, String descripcion, String marca) {
        if (marca=="Coca_Cola"){
        return new ProductoCocaCola(nombre, descripcion, marca);
    }
        else if (marca=="Pepsi"){
            return new ProductoPepsi(nombre, descripcion, marca);
                    }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
