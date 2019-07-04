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
public abstract class Persona {
    private String nombres;
    private String Apellidos;
    private int edad;
    private int telefono;
    private String direccion;
    private int rango;

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Persona(String nombres, String Apellidos, int edad, int telefono, String direccion,int rango) {
        setNombres(nombres);
        setApellidos(Apellidos);
        setEdad(edad);         
        setTelefono(telefono);
        setDireccion(direccion);   
        setRango(rango);

    }
    public abstract String getDescripcion();


    }

    

