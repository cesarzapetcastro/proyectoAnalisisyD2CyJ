/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.depositodeagua;
import com.mycompany.depositodeagua.Persona;
/**
 *
 * @author Jorge
 */
public class administrador extends Persona{

    public administrador(String nombres, String Apellidos, int edad, int telefono, String direccion, int rango) {
        super(nombres, Apellidos, edad, telefono, direccion, rango);
    }

    @Override
    public String getDescripcion() {
        return "se creo un Adminstrador"; //To change body of generated methods, choose Tools | Templates.
    }

    
}
