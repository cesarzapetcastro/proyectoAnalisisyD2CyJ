/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.depositodeagua;

import entidades.Vendedorsql;
import entidades.Bodeguerosql;
import entidades.Administradorsql;
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

     //   System.out.println(persona.getDescripcion());
        
    //    ProductoFactoryMethod agregaproducto = (ProductoFactoryMethod) new ProductoFactory ();
      //  Producto producto = agregaproducto.CrearProducto("Coca_cola", "Producto a base de cola", "Coca_Cola");
      //  System.out.println(producto.getInforme());
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("depositoagua");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Administradorsql> queryadmin = em.createNamedQuery("Administradorsql.findAll", Administradorsql.class);
        List<Administradorsql> listaAdmin = queryadmin.getResultList();

       // TypedQuery<Bodeguerosql> querybodeguero = em.createNamedQuery("Bodeguerosql.findAll", Bodeguerosql.class);
     //   List<Bodeguerosql> listaBodeguero = querybodeguero.getResultList();
        
     //   TypedQuery<Vendedorsql> queryvendedor = em.createNamedQuery("Vendedorsql.findAll", Vendedorsql.class);
     //   List<Vendedorsql> listaVendedor = queryvendedor.getResultList();
        
        for (Administradorsql tp : listaAdmin) {
            System.out.println(tp);
        }
       // for (Bodeguerosql tp : listaBodeguero) {
       //     System.out.println(tp);
        //}
      //  for (Vendedorsql tp : listaVendedor) {
        //    System.out.println(tp);
      //  }
       // em.close();
        
    }
    
}
