/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.depositodeagua;

import com.mycompany.depositodeagua.Proveedor;

/**
 *
 * @author MicroSistemas
 */
public class ProveedorMayorista extends Proveedor{

    public ProveedorMayorista(String nombre, String direccion, String telefono, String observacion, String tipo) {
        super(nombre, direccion, telefono, observacion, tipo);
    }

    @Override
    public String getDescripcion() {
        return "se creo un mayorista";
    }

 


}
