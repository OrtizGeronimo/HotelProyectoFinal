/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Habitacion;
import Logica.TipoHabitacion;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author yeron
 */
public class TipoHabitacionJpaController implements Serializable {

    public TipoHabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public TipoHabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("ProyectoFinalHotelPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoHabitacion tipoHabitacion) throws PreexistingEntityException, Exception {
        if (tipoHabitacion.getListaHabitaciones() == null) {
            tipoHabitacion.setListaHabitaciones(new ArrayList<Habitacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Habitacion> attachedListaHabitaciones = new ArrayList<Habitacion>();
            for (Habitacion listaHabitacionesHabitacionToAttach : tipoHabitacion.getListaHabitaciones()) {
                listaHabitacionesHabitacionToAttach = em.getReference(listaHabitacionesHabitacionToAttach.getClass(), listaHabitacionesHabitacionToAttach.getNum());
                attachedListaHabitaciones.add(listaHabitacionesHabitacionToAttach);
            }
            tipoHabitacion.setListaHabitaciones(attachedListaHabitaciones);
            em.persist(tipoHabitacion);
            for (Habitacion listaHabitacionesHabitacion : tipoHabitacion.getListaHabitaciones()) {
                TipoHabitacion oldTipoOfListaHabitacionesHabitacion = listaHabitacionesHabitacion.getTipo();
                listaHabitacionesHabitacion.setTipo(tipoHabitacion);
                listaHabitacionesHabitacion = em.merge(listaHabitacionesHabitacion);
                if (oldTipoOfListaHabitacionesHabitacion != null) {
                    oldTipoOfListaHabitacionesHabitacion.getListaHabitaciones().remove(listaHabitacionesHabitacion);
                    oldTipoOfListaHabitacionesHabitacion = em.merge(oldTipoOfListaHabitacionesHabitacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoHabitacion(tipoHabitacion.getNombreTipoHabitacion()) != null) {
                throw new PreexistingEntityException("TipoHabitacion " + tipoHabitacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoHabitacion tipoHabitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoHabitacion persistentTipoHabitacion = em.find(TipoHabitacion.class, tipoHabitacion.getNombreTipoHabitacion());
            List<Habitacion> listaHabitacionesOld = persistentTipoHabitacion.getListaHabitaciones();
            List<Habitacion> listaHabitacionesNew = tipoHabitacion.getListaHabitaciones();
            List<Habitacion> attachedListaHabitacionesNew = new ArrayList<Habitacion>();
            for (Habitacion listaHabitacionesNewHabitacionToAttach : listaHabitacionesNew) {
                listaHabitacionesNewHabitacionToAttach = em.getReference(listaHabitacionesNewHabitacionToAttach.getClass(), listaHabitacionesNewHabitacionToAttach.getNum());
                attachedListaHabitacionesNew.add(listaHabitacionesNewHabitacionToAttach);
            }
            listaHabitacionesNew = attachedListaHabitacionesNew;
            tipoHabitacion.setListaHabitaciones(listaHabitacionesNew);
            tipoHabitacion = em.merge(tipoHabitacion);
            for (Habitacion listaHabitacionesOldHabitacion : listaHabitacionesOld) {
                if (!listaHabitacionesNew.contains(listaHabitacionesOldHabitacion)) {
                    listaHabitacionesOldHabitacion.setTipo(null);
                    listaHabitacionesOldHabitacion = em.merge(listaHabitacionesOldHabitacion);
                }
            }
            for (Habitacion listaHabitacionesNewHabitacion : listaHabitacionesNew) {
                if (!listaHabitacionesOld.contains(listaHabitacionesNewHabitacion)) {
                    TipoHabitacion oldTipoOfListaHabitacionesNewHabitacion = listaHabitacionesNewHabitacion.getTipo();
                    listaHabitacionesNewHabitacion.setTipo(tipoHabitacion);
                    listaHabitacionesNewHabitacion = em.merge(listaHabitacionesNewHabitacion);
                    if (oldTipoOfListaHabitacionesNewHabitacion != null && !oldTipoOfListaHabitacionesNewHabitacion.equals(tipoHabitacion)) {
                        oldTipoOfListaHabitacionesNewHabitacion.getListaHabitaciones().remove(listaHabitacionesNewHabitacion);
                        oldTipoOfListaHabitacionesNewHabitacion = em.merge(oldTipoOfListaHabitacionesNewHabitacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoHabitacion.getNombreTipoHabitacion();
                if (findTipoHabitacion(id) == null) {
                    throw new NonexistentEntityException("The tipoHabitacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoHabitacion tipoHabitacion;
            try {
                tipoHabitacion = em.getReference(TipoHabitacion.class, id);
                tipoHabitacion.getNombreTipoHabitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoHabitacion with id " + id + " no longer exists.", enfe);
            }
            List<Habitacion> listaHabitaciones = tipoHabitacion.getListaHabitaciones();
            for (Habitacion listaHabitacionesHabitacion : listaHabitaciones) {
                listaHabitacionesHabitacion.setTipo(null);
                listaHabitacionesHabitacion = em.merge(listaHabitacionesHabitacion);
            }
            em.remove(tipoHabitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoHabitacion> findTipoHabitacionEntities() {
        return findTipoHabitacionEntities(true, -1, -1);
    }

    public List<TipoHabitacion> findTipoHabitacionEntities(int maxResults, int firstResult) {
        return findTipoHabitacionEntities(false, maxResults, firstResult);
    }

    private List<TipoHabitacion> findTipoHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoHabitacion.class));
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

    public TipoHabitacion findTipoHabitacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoHabitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoHabitacion> rt = cq.from(TipoHabitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
