/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


/**
 *
 * @author MicroSistemas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PersonaFactoryMethod fabrica = new PersonaFactory ();
        Persona persona = fabrica.crearPersona("cesar", "zapet", 0, 0, "direccion",2);
        System.out.println(persona.getDescripcion());
    }
    
}
