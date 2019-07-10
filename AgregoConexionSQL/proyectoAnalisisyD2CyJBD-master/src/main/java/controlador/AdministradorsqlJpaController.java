/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import entidades.Administradorsql;
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
public class AdministradorsqlJpaController implements Serializable {

    public AdministradorsqlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administradorsql administradorsql) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(administradorsql);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAdministradorsql(administradorsql.getId()) != null) {
                throw new PreexistingEntityException("Administradorsql " + administradorsql + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administradorsql administradorsql) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            administradorsql = em.merge(administradorsql);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administradorsql.getId();
                if (findAdministradorsql(id) == null) {
                    throw new NonexistentEntityException("The administradorsql with id " + id + " no longer exists.");
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
            Administradorsql administradorsql;
            try {
                administradorsql = em.getReference(Administradorsql.class, id);
                administradorsql.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administradorsql with id " + id + " no longer exists.", enfe);
            }
            em.remove(administradorsql);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administradorsql> findAdministradorsqlEntities() {
        return findAdministradorsqlEntities(true, -1, -1);
    }

    public List<Administradorsql> findAdministradorsqlEntities(int maxResults, int firstResult) {
        return findAdministradorsqlEntities(false, maxResults, firstResult);
    }

    private List<Administradorsql> findAdministradorsqlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administradorsql.class));
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

    public Administradorsql findAdministradorsql(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administradorsql.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorsqlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administradorsql> rt = cq.from(Administradorsql.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
