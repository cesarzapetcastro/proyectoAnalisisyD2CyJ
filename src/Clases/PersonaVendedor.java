/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;



/**
 *
 * @author Jorge
 */
public class PersonaVendedor extends Persona{

    public PersonaVendedor(String nombres, String Apellidos, int edad, int telefono, String direccion,int rango) {
        super(nombres, Apellidos, edad, telefono, direccion,rango);
    }
 

    @Override
    public String getDescripcion() {
        return "se creo una persona vendedor";
    }
    
}

