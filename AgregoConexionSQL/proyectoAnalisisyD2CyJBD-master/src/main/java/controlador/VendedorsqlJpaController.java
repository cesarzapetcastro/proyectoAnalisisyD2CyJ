/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import entidades.Vendedorsql;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MicroSistemas
 */
public class VendedorsqlJpaController implements Serializable {

    public VendedorsqlJpaController(EntityManagerFactory emf) {
        this.emf = Persistence.createEntityManagerFactory("depositoagua");
    }
    private EntityManagerFactory emf = null;

    public VendedorsqlJpaController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vendedorsql vendedorsql) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vendedorsql);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVendedorsql(vendedorsql.getId()) != null) {
                throw new PreexistingEntityException("Vendedorsql " + vendedorsql + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vendedorsql vendedorsql) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vendedorsql = em.merge(vendedorsql);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vendedorsql.getId();
                if (findVendedorsql(id) == null) {
                    throw new NonexistentEntityException("The vendedorsql with id " + id + " no longer exists.");
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
            Vendedorsql vendedorsql;
            try {
                vendedorsql = em.getReference(Vendedorsql.class, id);
                vendedorsql.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendedorsql with id " + id + " no longer exists.", enfe);
            }
            em.remove(vendedorsql);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vendedorsql> findVendedorsqlEntities() {
        return findVendedorsqlEntities(true, -1, -1);
    }

    public List<Vendedorsql> findVendedorsqlEntities(int maxResults, int firstResult) {
        return findVendedorsqlEntities(false, maxResults, firstResult);
    }

    private List<Vendedorsql> findVendedorsqlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vendedorsql.class));
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

    public Vendedorsql findVendedorsql(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vendedorsql.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendedorsqlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vendedorsql> rt = cq.from(Vendedorsql.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
