/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Habitacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.TipoHabitacion;
import Logica.Reserva;
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
public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public HabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("ProyectoFinalHotelPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) throws PreexistingEntityException, Exception {
        if (habitacion.getListaReservas() == null) {
            habitacion.setListaReservas(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoHabitacion tipo = habitacion.getTipo();
            if (tipo != null) {
                tipo = em.getReference(tipo.getClass(), tipo.getNombreTipoHabitacion());
                habitacion.setTipo(tipo);
            }
            List<Reserva> attachedListaReservas = new ArrayList<Reserva>();
            for (Reserva listaReservasReservaToAttach : habitacion.getListaReservas()) {
                listaReservasReservaToAttach = em.getReference(listaReservasReservaToAttach.getClass(), listaReservasReservaToAttach.getIdReserva());
                attachedListaReservas.add(listaReservasReservaToAttach);
            }
            habitacion.setListaReservas(attachedListaReservas);
            em.persist(habitacion);
            if (tipo != null) {
                tipo.getListaHabitaciones().add(habitacion);
                tipo = em.merge(tipo);
            }
            for (Reserva listaReservasReserva : habitacion.getListaReservas()) {
                Habitacion oldHabitacionOfListaReservasReserva = listaReservasReserva.getHabitacion();
                listaReservasReserva.setHabitacion(habitacion);
                listaReservasReserva = em.merge(listaReservasReserva);
                if (oldHabitacionOfListaReservasReserva != null) {
                    oldHabitacionOfListaReservasReserva.getListaReservas().remove(listaReservasReserva);
                    oldHabitacionOfListaReservasReserva = em.merge(oldHabitacionOfListaReservasReserva);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHabitacion(habitacion.getNum()) != null) {
                throw new PreexistingEntityException("Habitacion " + habitacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getNum());
            TipoHabitacion tipoOld = persistentHabitacion.getTipo();
            TipoHabitacion tipoNew = habitacion.getTipo();
            List<Reserva> listaReservasOld = persistentHabitacion.getListaReservas();
            List<Reserva> listaReservasNew = habitacion.getListaReservas();
            if (tipoNew != null) {
                tipoNew = em.getReference(tipoNew.getClass(), tipoNew.getNombreTipoHabitacion());
                habitacion.setTipo(tipoNew);
            }
            List<Reserva> attachedListaReservasNew = new ArrayList<Reserva>();
            for (Reserva listaReservasNewReservaToAttach : listaReservasNew) {
                listaReservasNewReservaToAttach = em.getReference(listaReservasNewReservaToAttach.getClass(), listaReservasNewReservaToAttach.getIdReserva());
                attachedListaReservasNew.add(listaReservasNewReservaToAttach);
            }
            listaReservasNew = attachedListaReservasNew;
            habitacion.setListaReservas(listaReservasNew);
            habitacion = em.merge(habitacion);
            if (tipoOld != null && !tipoOld.equals(tipoNew)) {
                tipoOld.getListaHabitaciones().remove(habitacion);
                tipoOld = em.merge(tipoOld);
            }
            if (tipoNew != null && !tipoNew.equals(tipoOld)) {
                tipoNew.getListaHabitaciones().add(habitacion);
                tipoNew = em.merge(tipoNew);
            }
            for (Reserva listaReservasOldReserva : listaReservasOld) {
                if (!listaReservasNew.contains(listaReservasOldReserva)) {
                    listaReservasOldReserva.setHabitacion(null);
                    listaReservasOldReserva = em.merge(listaReservasOldReserva);
                }
            }
            for (Reserva listaReservasNewReserva : listaReservasNew) {
                if (!listaReservasOld.contains(listaReservasNewReserva)) {
                    Habitacion oldHabitacionOfListaReservasNewReserva = listaReservasNewReserva.getHabitacion();
                    listaReservasNewReserva.setHabitacion(habitacion);
                    listaReservasNewReserva = em.merge(listaReservasNewReserva);
                    if (oldHabitacionOfListaReservasNewReserva != null && !oldHabitacionOfListaReservasNewReserva.equals(habitacion)) {
                        oldHabitacionOfListaReservasNewReserva.getListaReservas().remove(listaReservasNewReserva);
                        oldHabitacionOfListaReservasNewReserva = em.merge(oldHabitacionOfListaReservasNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = habitacion.getNum();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            TipoHabitacion tipo = habitacion.getTipo();
            if (tipo != null) {
                tipo.getListaHabitaciones().remove(habitacion);
                tipo = em.merge(tipo);
            }
            List<Reserva> listaReservas = habitacion.getListaReservas();
            for (Reserva listaReservasReserva : listaReservas) {
                listaReservasReserva.setHabitacion(null);
                listaReservasReserva = em.merge(listaReservasReserva);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
