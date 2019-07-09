/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.depositodeagua;


import com.mycompany.depositodeagua.PersonaBodeguero;
import com.mycompany.depositodeagua.Persona;



/**
 *
 * @author Jorge
 */
public class PersonaFactory implements PersonaFactoryMethod{

    @Override
    public Persona crearPersona(String nombres, String Apellidos, int edad, int telefono, String direccion, String rango) {
        
        
        if ((rango=="Vendedor")||(rango=="vendedor")){
        return new PersonaVendedor(nombres, Apellidos, edad, telefono, direccion, rango);
    }
else if ((rango=="Bodeguero")||(rango=="Bodeguero")){
            return new PersonaBodeguero(nombres, Apellidos, edad, telefono, direccion, rango);
                    }
else if ((rango=="Administrador")||(rango=="administrador")){
            return new administrador(nombres, Apellidos, edad, telefono, direccion, rango);
                    }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

   
    

