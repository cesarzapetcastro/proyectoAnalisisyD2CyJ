/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.depositodeagua;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;




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
        ProveedorFactoryMethod fabrica1 = new ProveedorFactory ();
        Proveedor proveedor = fabrica1.crearProveedor("cesar", "SAN MARCOS","1234567","OBSERVACION","minorista");
        System.out.println(proveedor.getDescripcion());
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("depositoagua");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Administradorsql> queryadmin = em.createNamedQuery("Administradorsql.findAll", Administradorsql.class);
        List<Administradorsql> listaAdmin = queryadmin.getResultList();

        TypedQuery<Bodeguerosql> querybodeguero = em.createNamedQuery("Bodeguerosql.findAll", Bodeguerosql.class);
        List<Bodeguerosql> listaBodeguero = querybodeguero.getResultList();
        
        TypedQuery<Vendedorsql> queryvendedor = em.createNamedQuery("Vendedorsql.findAll", Vendedorsql.class);
        List<Vendedorsql> listaVendedor = queryvendedor.getResultList();
        
        for (Administradorsql tp : listaAdmin) {
            System.out.println(tp);
        }
        for (Bodeguerosql tp : listaBodeguero) {
            System.out.println(tp);
        }
        for (Vendedorsql tp : listaVendedor) {
            System.out.println(tp);
        }
        em.close();
        ;
    }
    
}
