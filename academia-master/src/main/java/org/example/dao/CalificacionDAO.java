package org.example.dao;

// Importamos la clase modelo Calificacion
import org.example.model.Calificacion;

// Importaciones de JPA / Jakarta Persistence
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List; // Para manejar listas de calificaciones

// Clase DAO: maneja todas las operaciones de la base de datos para Calificacion
public class CalificacionDAO {

    // Creamos una fábrica de EntityManager a partir del persistence unit definido
    // en persistence.xml
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("academiaPU");

    // --- INSERTAR una calificacion ---
    public void insertar(Calificacion c) {
        EntityManager em = emf.createEntityManager(); // Creamos un EntityManager para esta operación
        try {
            em.getTransaction().begin(); // Iniciamos la transacción
            em.persist(c); // Guardamos el objeto Calificacion en la base de datos
            em.getTransaction().commit(); // Confirmamos los cambios (commit)
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback(); // Si algo falla, deshacer cambios
            em.close(); // Cerramos el EntityManager
        }
    }

    // --- LISTAR todas las calificaciones ---
    public List<Calificacion> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL: consulta los objetos Calificacion en la base de datos
            return em.createQuery("SELECT c FROM Calificacion c", Calificacion.class)
                    .getResultList(); // Devuelve una lista de Calificacion
        } finally {
            em.close(); // Siempre cerrar el EntityManager
        }
    }

    // --- BUSCAR una calificacion por ID ---
    public Calificacion buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Calificacion.class, id); // Busca usando la clave primaria
        } finally {
            em.close();
        }
    }

    // --- ACTUALIZAR una calificacion ---
    public void actualizar(Calificacion c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c); // Merge actualiza el objeto en la base de datos
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback(); // deshacer cambios si falla
            em.close();
        }
    }

    // --- ELIMINAR una calificacion por ID ---
    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Calificacion c = em.find(Calificacion.class, id); // Buscar objeto a eliminar
            if (c != null) {
                em.remove(c); // Eliminar solo si existe
            }
            em.getTransaction().commit(); // Confirmar eliminación
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback(); // deshacer si falla
            em.close();
        }
    }

    // --- BUSCAR calificaciones por alumno ID ---
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
