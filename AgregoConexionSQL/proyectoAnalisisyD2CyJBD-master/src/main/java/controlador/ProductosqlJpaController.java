/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Bodegasql;
import entidades.Productosql;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MicroSistemas
 */
public class ProductosqlJpaController implements Serializable {

    public ProductosqlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productosql productosql) {
        if (productosql.getBodegasqlCollection() == null) {
            productosql.setBodegasqlCollection(new ArrayList<Bodegasql>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Bodegasql> attachedBodegasqlCollection = new ArrayList<Bodegasql>();
            for (Bodegasql bodegasqlCollectionBodegasqlToAttach : productosql.getBodegasqlCollection()) {
                bodegasqlCollectionBodegasqlToAttach = em.getReference(bodegasqlCollectionBodegasqlToAttach.getClass(), bodegasqlCollectionBodegasqlToAttach.getIdBodega());
                attachedBodegasqlCollection.add(bodegasqlCollectionBodegasqlToAttach);
            }
            productosql.setBodegasqlCollection(attachedBodegasqlCollection);
            em.persist(productosql);
            for (Bodegasql bodegasqlCollectionBodegasql : productosql.getBodegasqlCollection()) {
                Productosql oldProductosqlidProductoOfBodegasqlCollectionBodegasql = bodegasqlCollectionBodegasql.getProductosqlidProducto();
                bodegasqlCollectionBodegasql.setProductosqlidProducto(productosql);
                bodegasqlCollectionBodegasql = em.merge(bodegasqlCollectionBodegasql);
                if (oldProductosqlidProductoOfBodegasqlCollectionBodegasql != null) {
                    oldProductosqlidProductoOfBodegasqlCollectionBodegasql.getBodegasqlCollection().remove(bodegasqlCollectionBodegasql);
                    oldProductosqlidProductoOfBodegasqlCollectionBodegasql = em.merge(oldProductosqlidProductoOfBodegasqlCollectionBodegasql);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productosql productosql) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productosql persistentProductosql = em.find(Productosql.class, productosql.getIdProducto());
            Collection<Bodegasql> bodegasqlCollectionOld = persistentProductosql.getBodegasqlCollection();
            Collection<Bodegasql> bodegasqlCollectionNew = productosql.getBodegasqlCollection();
            List<String> illegalOrphanMessages = null;
            for (Bodegasql bodegasqlCollectionOldBodegasql : bodegasqlCollectionOld) {
                if (!bodegasqlCollectionNew.contains(bodegasqlCollectionOldBodegasql)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bodegasql " + bodegasqlCollectionOldBodegasql + " since its productosqlidProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Bodegasql> attachedBodegasqlCollectionNew = new ArrayList<Bodegasql>();
            for (Bodegasql bodegasqlCollectionNewBodegasqlToAttach : bodegasqlCollectionNew) {
                bodegasqlCollectionNewBodegasqlToAttach = em.getReference(bodegasqlCollectionNewBodegasqlToAttach.getClass(), bodegasqlCollectionNewBodegasqlToAttach.getIdBodega());
                attachedBodegasqlCollectionNew.add(bodegasqlCollectionNewBodegasqlToAttach);
            }
            bodegasqlCollectionNew = attachedBodegasqlCollectionNew;
            productosql.setBodegasqlCollection(bodegasqlCollectionNew);
            productosql = em.merge(productosql);
            for (Bodegasql bodegasqlCollectionNewBodegasql : bodegasqlCollectionNew) {
                if (!bodegasqlCollectionOld.contains(bodegasqlCollectionNewBodegasql)) {
                    Productosql oldProductosqlidProductoOfBodegasqlCollectionNewBodegasql = bodegasqlCollectionNewBodegasql.getProductosqlidProducto();
                    bodegasqlCollectionNewBodegasql.setProductosqlidProducto(productosql);
                    bodegasqlCollectionNewBodegasql = em.merge(bodegasqlCollectionNewBodegasql);
                    if (oldProductosqlidProductoOfBodegasqlCollectionNewBodegasql != null && !oldProductosqlidProductoOfBodegasqlCollectionNewBodegasql.equals(productosql)) {
                        oldProductosqlidProductoOfBodegasqlCollectionNewBodegasql.getBodegasqlCollection().remove(bodegasqlCollectionNewBodegasql);
                        oldProductosqlidProductoOfBodegasqlCollectionNewBodegasql = em.merge(oldProductosqlidProductoOfBodegasqlCollectionNewBodegasql);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productosql.getIdProducto();
                if (findProductosql(id) == null) {
                    throw new NonexistentEntityException("The productosql with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productosql productosql;
            try {
                productosql = em.getReference(Productosql.class, id);
                productosql.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productosql with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Bodegasql> bodegasqlCollectionOrphanCheck = productosql.getBodegasqlCollection();
            for (Bodegasql bodegasqlCollectionOrphanCheckBodegasql : bodegasqlCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productosql (" + productosql + ") cannot be destroyed since the Bodegasql " + bodegasqlCollectionOrphanCheckBodegasql + " in its bodegasqlCollection field has a non-nullable productosqlidProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(productosql);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productosql> findProductosqlEntities() {
        return findProductosqlEntities(true, -1, -1);
    }

    public List<Productosql> findProductosqlEntities(int maxResults, int firstResult) {
        return findProductosqlEntities(false, maxResults, firstResult);
    }

    private List<Productosql> findProductosqlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productosql.class));
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

    public Productosql findProductosql(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productosql.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosqlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productosql> rt = cq.from(Productosql.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
