/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.depositodeagua;

/**
 *
 * @author MicroSistemas
 */
public class ProveedorFactory implements ProveedorFactoryMethod{

    @Override
    public Proveedor crearProveedor(String nombre, String direccion, String telefono, String observacion, String tipo) {
       if ((tipo=="mayorista")||(tipo=="Mayorista")){
        return new ProveedorMayorista(nombre,direccion,telefono,observacion,tipo);
    }
else if ((tipo=="Minorista")||(tipo=="minorista")){
            return new ProveedorMinorista(nombre, direccion, telefono, observacion, tipo);
                    }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
