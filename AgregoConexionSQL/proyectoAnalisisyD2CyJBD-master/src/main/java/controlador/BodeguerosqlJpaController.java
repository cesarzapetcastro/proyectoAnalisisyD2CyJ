/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import entidades.Bodeguerosql;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MicroSistemas
 */
public class BodeguerosqlJpaController implements Serializable {

    public BodeguerosqlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bodeguerosql bodeguerosql) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bodeguerosql);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBodeguerosql(bodeguerosql.getId()) != null) {
                throw new PreexistingEntityException("Bodeguerosql " + bodeguerosql + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bodeguerosql bodeguerosql) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bodeguerosql = em.merge(bodeguerosql);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bodeguerosql.getId();
                if (findBodeguerosql(id) == null) {
                    throw new NonexistentEntityException("The bodeguerosql with id " + id + " no longer exists.");
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
            Bodeguerosql bodeguerosql;
            try {
                bodeguerosql = em.getReference(Bodeguerosql.class, id);
                bodeguerosql.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bodeguerosql with id " + id + " no longer exists.", enfe);
            }
            em.remove(bodeguerosql);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bodeguerosql> findBodeguerosqlEntities() {
        return findBodeguerosqlEntities(true, -1, -1);
    }

    public List<Bodeguerosql> findBodeguerosqlEntities(int maxResults, int firstResult) {
        return findBodeguerosqlEntities(false, maxResults, firstResult);
    }

    private List<Bodeguerosql> findBodeguerosqlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bodeguerosql.class));
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

    public Bodeguerosql findBodeguerosql(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bodeguerosql.class, id);
        } finally {
            em.close();
        }
    }

    public int getBodeguerosqlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bodeguerosql> rt = cq.from(Bodeguerosql.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
