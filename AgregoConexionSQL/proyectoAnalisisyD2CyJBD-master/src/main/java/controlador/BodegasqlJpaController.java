/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import entidades.Bodegasql;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Productosql;
import entidades.Proveedorsql;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MicroSistemas
 */
public class BodegasqlJpaController implements Serializable {

    public BodegasqlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bodegasql bodegasql) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productosql productosqlidProducto = bodegasql.getProductosqlidProducto();
            if (productosqlidProducto != null) {
                productosqlidProducto = em.getReference(productosqlidProducto.getClass(), productosqlidProducto.getIdProducto());
                bodegasql.setProductosqlidProducto(productosqlidProducto);
            }
            Proveedorsql proveedorsqlidProveedorsql = bodegasql.getProveedorsqlidProveedorsql();
            if (proveedorsqlidProveedorsql != null) {
                proveedorsqlidProveedorsql = em.getReference(proveedorsqlidProveedorsql.getClass(), proveedorsqlidProveedorsql.getIdProveedorsql());
                bodegasql.setProveedorsqlidProveedorsql(proveedorsqlidProveedorsql);
            }
            em.persist(bodegasql);
            if (productosqlidProducto != null) {
                productosqlidProducto.getBodegasqlCollection().add(bodegasql);
                productosqlidProducto = em.merge(productosqlidProducto);
            }
            if (proveedorsqlidProveedorsql != null) {
                proveedorsqlidProveedorsql.getBodegasqlCollection().add(bodegasql);
                proveedorsqlidProveedorsql = em.merge(proveedorsqlidProveedorsql);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bodegasql bodegasql) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bodegasql persistentBodegasql = em.find(Bodegasql.class, bodegasql.getIdBodega());
            Productosql productosqlidProductoOld = persistentBodegasql.getProductosqlidProducto();
            Productosql productosqlidProductoNew = bodegasql.getProductosqlidProducto();
            Proveedorsql proveedorsqlidProveedorsqlOld = persistentBodegasql.getProveedorsqlidProveedorsql();
            Proveedorsql proveedorsqlidProveedorsqlNew = bodegasql.getProveedorsqlidProveedorsql();
            if (productosqlidProductoNew != null) {
                productosqlidProductoNew = em.getReference(productosqlidProductoNew.getClass(), productosqlidProductoNew.getIdProducto());
                bodegasql.setProductosqlidProducto(productosqlidProductoNew);
            }
            if (proveedorsqlidProveedorsqlNew != null) {
                proveedorsqlidProveedorsqlNew = em.getReference(proveedorsqlidProveedorsqlNew.getClass(), proveedorsqlidProveedorsqlNew.getIdProveedorsql());
                bodegasql.setProveedorsqlidProveedorsql(proveedorsqlidProveedorsqlNew);
            }
            bodegasql = em.merge(bodegasql);
            if (productosqlidProductoOld != null && !productosqlidProductoOld.equals(productosqlidProductoNew)) {
                productosqlidProductoOld.getBodegasqlCollection().remove(bodegasql);
                productosqlidProductoOld = em.merge(productosqlidProductoOld);
            }
            if (productosqlidProductoNew != null && !productosqlidProductoNew.equals(productosqlidProductoOld)) {
                productosqlidProductoNew.getBodegasqlCollection().add(bodegasql);
                productosqlidProductoNew = em.merge(productosqlidProductoNew);
            }
            if (proveedorsqlidProveedorsqlOld != null && !proveedorsqlidProveedorsqlOld.equals(proveedorsqlidProveedorsqlNew)) {
                proveedorsqlidProveedorsqlOld.getBodegasqlCollection().remove(bodegasql);
                proveedorsqlidProveedorsqlOld = em.merge(proveedorsqlidProveedorsqlOld);
            }
            if (proveedorsqlidProveedorsqlNew != null && !proveedorsqlidProveedorsqlNew.equals(proveedorsqlidProveedorsqlOld)) {
                proveedorsqlidProveedorsqlNew.getBodegasqlCollection().add(bodegasql);
                proveedorsqlidProveedorsqlNew = em.merge(proveedorsqlidProveedorsqlNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bodegasql.getIdBodega();
                if (findBodegasql(id) == null) {
                    throw new NonexistentEntityException("The bodegasql with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bodegasql bodegasql;
            try {
                bodegasql = em.getReference(Bodegasql.class, id);
                bodegasql.getIdBodega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bodegasql with id " + id + " no longer exists.", enfe);
            }
            Productosql productosqlidProducto = bodegasql.getProductosqlidProducto();
            if (productosqlidProducto != null) {
                productosqlidProducto.getBodegasqlCollection().remove(bodegasql);
                productosqlidProducto = em.merge(productosqlidProducto);
            }
            Proveedorsql proveedorsqlidProveedorsql = bodegasql.getProveedorsqlidProveedorsql();
            if (proveedorsqlidProveedorsql != null) {
                proveedorsqlidProveedorsql.getBodegasqlCollection().remove(bodegasql);
                proveedorsqlidProveedorsql = em.merge(proveedorsqlidProveedorsql);
            }
            em.remove(bodegasql);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bodegasql> findBodegasqlEntities() {
        return findBodegasqlEntities(true, -1, -1);
    }

    public List<Bodegasql> findBodegasqlEntities(int maxResults, int firstResult) {
        return findBodegasqlEntities(false, maxResults, firstResult);
    }

    private List<Bodegasql> findBodegasqlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bodegasql.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bodegasql findBodegasql(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bodegasql.class, id);
        } finally {
            em.close();
        }
    }

    public int getBodegasqlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bodegasql> rt = cq.from(Bodegasql.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
