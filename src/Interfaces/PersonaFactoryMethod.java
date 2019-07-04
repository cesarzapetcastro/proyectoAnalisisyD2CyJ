/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.Persona;

/**
 *
 * @author MicroSistemas
 */
public interface PersonaFactoryMethod {
    public Persona crearPersona (String nombres, String Apellidos, int edad, int telefono, String direccion);
}
