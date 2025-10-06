package org.example.dao;

// Importamos la clase modelo Alumno
import org.example.model.Alumno;

// Importaciones de JPA / Jakarta Persistence
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List; // Para manejar listas de alumnos

// Clase DAO: maneja todas las operaciones de la base de datos para Alumno
public class AlumnoDAO {

    // Creamos una fábrica de EntityManager a partir del persistence unit definido
    // en persistence.xml
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("academiaPU");

    // --- INSERTAR un alumno ---
    public void insertar(Alumno a) {
        EntityManager em = emf.createEntityManager(); // Creamos un EntityManager para esta operación
        try {
            em.getTransaction().begin(); // Iniciamos la transacción
            em.persist(a); // Guardamos el objeto Alumno en la base de datos
            em.getTransaction().commit(); // Confirmamos los cambios (commit)
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback(); // Si algo falla, deshacer cambios
            em.close(); // Cerramos el EntityManager
        }
    }

    // --- LISTAR todos los alumnos ---
    public List<Alumno> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL: consulta los objetos Alumno en la base de datos
            return em.createQuery("SELECT a FROM Alumno a", Alumno.class)
                    .getResultList(); // Devuelve una lista de Alumno
        } finally {
            em.close(); // Siempre cerrar el EntityManager
        }
    }

    // --- BUSCAR un alumno por ID ---
    public Alumno buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Alumno.class, id); // Busca usando la clave primaria
        } finally {
            em.close();
        }
    }

    // --- ACTUALIZAR un alumno ---
    public void actualizar(Alumno a) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(a); // Merge actualiza el objeto en la base de datos
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback(); // deshacer cambios si falla
            em.close();
        }
    }

    // --- ELIMINAR un alumno por ID ---
    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Alumno a = em.find(Alumno.class, id); // Buscar objeto a eliminar
            if (a != null) {
                em.remove(a); // Eliminar solo si existe
            }
            em.getTransaction().commit(); // Confirmar eliminación
        } finally {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback(); // deshacer si falla
            em.close();
        }
    }
}
