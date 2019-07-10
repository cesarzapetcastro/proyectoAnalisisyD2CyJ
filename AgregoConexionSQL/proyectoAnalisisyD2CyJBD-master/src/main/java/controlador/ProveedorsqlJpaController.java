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
import entidades.Proveedorsql;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MicroSistemas
 */
public class ProveedorsqlJpaController implements Serializable {

    public ProveedorsqlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedorsql proveedorsql) {
        if (proveedorsql.getBodegasqlCollection() == null) {
            proveedorsql.setBodegasqlCollection(new ArrayList<Bodegasql>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Bodegasql> attachedBodegasqlCollection = new ArrayList<Bodegasql>();
            for (Bodegasql bodegasqlCollectionBodegasqlToAttach : proveedorsql.getBodegasqlCollection()) {
                bodegasqlCollectionBodegasqlToAttach = em.getReference(bodegasqlCollectionBodegasqlToAttach.getClass(), bodegasqlCollectionBodegasqlToAttach.getIdBodega());
                attachedBodegasqlCollection.add(bodegasqlCollectionBodegasqlToAttach);
            }
            proveedorsql.setBodegasqlCollection(attachedBodegasqlCollection);
            em.persist(proveedorsql);
            for (Bodegasql bodegasqlCollectionBodegasql : proveedorsql.getBodegasqlCollection()) {
                Proveedorsql oldProveedorsqlidProveedorsqlOfBodegasqlCollectionBodegasql = bodegasqlCollectionBodegasql.getProveedorsqlidProveedorsql();
                bodegasqlCollectionBodegasql.setProveedorsqlidProveedorsql(proveedorsql);
                bodegasqlCollectionBodegasql = em.merge(bodegasqlCollectionBodegasql);
                if (oldProveedorsqlidProveedorsqlOfBodegasqlCollectionBodegasql != null) {
                    oldProveedorsqlidProveedorsqlOfBodegasqlCollectionBodegasql.getBodegasqlCollection().remove(bodegasqlCollectionBodegasql);
                    oldProveedorsqlidProveedorsqlOfBodegasqlCollectionBodegasql = em.merge(oldProveedorsqlidProveedorsqlOfBodegasqlCollectionBodegasql);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedorsql proveedorsql) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedorsql persistentProveedorsql = em.find(Proveedorsql.class, proveedorsql.getIdProveedorsql());
            Collection<Bodegasql> bodegasqlCollectionOld = persistentProveedorsql.getBodegasqlCollection();
            Collection<Bodegasql> bodegasqlCollectionNew = proveedorsql.getBodegasqlCollection();
            List<String> illegalOrphanMessages = null;
            for (Bodegasql bodegasqlCollectionOldBodegasql : bodegasqlCollectionOld) {
                if (!bodegasqlCollectionNew.contains(bodegasqlCollectionOldBodegasql)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bodegasql " + bodegasqlCollectionOldBodegasql + " since its proveedorsqlidProveedorsql field is not nullable.");
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
            proveedorsql.setBodegasqlCollection(bodegasqlCollectionNew);
            proveedorsql = em.merge(proveedorsql);
            for (Bodegasql bodegasqlCollectionNewBodegasql : bodegasqlCollectionNew) {
                if (!bodegasqlCollectionOld.contains(bodegasqlCollectionNewBodegasql)) {
                    Proveedorsql oldProveedorsqlidProveedorsqlOfBodegasqlCollectionNewBodegasql = bodegasqlCollectionNewBodegasql.getProveedorsqlidProveedorsql();
                    bodegasqlCollectionNewBodegasql.setProveedorsqlidProveedorsql(proveedorsql);
                    bodegasqlCollectionNewBodegasql = em.merge(bodegasqlCollectionNewBodegasql);
                    if (oldProveedorsqlidProveedorsqlOfBodegasqlCollectionNewBodegasql != null && !oldProveedorsqlidProveedorsqlOfBodegasqlCollectionNewBodegasql.equals(proveedorsql)) {
                        oldProveedorsqlidProveedorsqlOfBodegasqlCollectionNewBodegasql.getBodegasqlCollection().remove(bodegasqlCollectionNewBodegasql);
                        oldProveedorsqlidProveedorsqlOfBodegasqlCollectionNewBodegasql = em.merge(oldProveedorsqlidProveedorsqlOfBodegasqlCollectionNewBodegasql);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedorsql.getIdProveedorsql();
                if (findProveedorsql(id) == null) {
                    throw new NonexistentEntityException("The proveedorsql with id " + id + " no longer exists.");
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
            Proveedorsql proveedorsql;
            try {
                proveedorsql = em.getReference(Proveedorsql.class, id);
                proveedorsql.getIdProveedorsql();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedorsql with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Bodegasql> bodegasqlCollectionOrphanCheck = proveedorsql.getBodegasqlCollection();
            for (Bodegasql bodegasqlCollectionOrphanCheckBodegasql : bodegasqlCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedorsql (" + proveedorsql + ") cannot be destroyed since the Bodegasql " + bodegasqlCollectionOrphanCheckBodegasql + " in its bodegasqlCollection field has a non-nullable proveedorsqlidProveedorsql field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedorsql);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedorsql> findProveedorsqlEntities() {
        return findProveedorsqlEntities(true, -1, -1);
    }

    public List<Proveedorsql> findProveedorsqlEntities(int maxResults, int firstResult) {
        return findProveedorsqlEntities(false, maxResults, firstResult);
    }

    private List<Proveedorsql> findProveedorsqlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedorsql.class));
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

    public Proveedorsql findProveedorsql(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedorsql.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorsqlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedorsql> rt = cq.from(Proveedorsql.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
