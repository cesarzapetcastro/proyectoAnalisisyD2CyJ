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
public class PersonaFactory implements PersonaFactoryMethod{

    @Override
    public Persona crearPersona(String nombres, String Apellidos, int edad, int telefono, String direccion, int rango) {
            if (rango==1){
        return new PersonaVendedor(nombres, Apellidos, 0, 0, direccion, 0);
    }
else if (rango==2){
            return new PersonaBodeguero(nombres, Apellidos, 0, 0, direccion, 0);
                    }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

   
    

