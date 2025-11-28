package org.example.dao;

import org.example.model.Materia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import org.example.dao.CalificacionDAO;
import org.example.model.Calificacion;

public class MateriaDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("academiaPU");

    public void insertar(Materia m) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }

    public Materia buscarPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Materia> res = em.createQuery("SELECT m FROM Materia m WHERE m.nombre = :n", Materia.class)
                    .setParameter("n", nombre)
                    .getResultList();
            return res.isEmpty() ? null : res.get(0);
        } finally {
            em.close();
        }
    }

    public Materia buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }

    public List<Materia> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Materia m", Materia.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        // Comprobar si existen calificaciones asociadas
        CalificacionDAO cdao = new CalificacionDAO();
        List<Calificacion> lista = cdao.buscarPorMateria(id);
        if (lista != null && !lista.isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la materia: tiene calificaciones asociadas.");
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Materia m = em.find(Materia.class, id);
            if (m != null) {
                em.remove(m);
            }
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }
}
