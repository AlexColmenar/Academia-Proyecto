package org.example.dao;

import org.example.model.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AlumnoDAO {
    // DAO para la entidad `Alumno`. Proporciona métodos CRUD básicos
    // cada método abre/ cierra su propio `EntityManager`

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("academiaPU");

    public void insertar(Alumno a) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }

    public List<Alumno> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Alumno a", Alumno.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizar(Alumno a) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(a);
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
            Alumno a = em.find(Alumno.class, id);
            if (a != null) {
                em.remove(a);
            }
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }
}
