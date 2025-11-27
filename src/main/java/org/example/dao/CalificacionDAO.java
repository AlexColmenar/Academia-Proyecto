package org.example.dao;

import org.example.model.Calificacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CalificacionDAO {
    // DAO para la entidad `Calificacion`. Métodos CRUD y una consulta
    // auxiliar `buscarPorAlumno` para recuperar calificaciones de un alumno.

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("academiaPU");

    public void insertar(Calificacion c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }

    public List<Calificacion> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Calificacion c", Calificacion.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Calificacion buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Calificacion.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizar(Calificacion c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Calificacion c = em.find(Calificacion.class, id);
            if (c != null) {
                em.remove(c);
            }
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }

    public List<Calificacion> buscarPorAlumno(Long alumnoId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Calificacion c WHERE c.alumno.id = :alumnoId", Calificacion.class)
                    .setParameter("alumnoId", alumnoId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
