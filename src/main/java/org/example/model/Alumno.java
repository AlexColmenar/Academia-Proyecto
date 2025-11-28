package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Estudiantes")
public class Alumno {
    // Campos: id, nombre, email, carrera y lista de calificaciones.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Email")
    private String email;

    @Column(name = "Carrera")
    private String carrera;
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Calificacion> calificaciones = new ArrayList<>();

    public Alumno() {
    }

    public Alumno(String nombre, String email, String carrera) {
        this.nombre = nombre;
        this.email = email;
        this.carrera = carrera;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    // Helper methods to maintain both sides of the relationship
    public void addCalificacion(Calificacion c) {
        if (c == null)
            return;
        calificaciones.add(c);
        c.setAlumno(this);
    }

    public void removeCalificacion(Calificacion c) {
        if (c == null)
            return;
        calificaciones.remove(c);
        c.setAlumno(null);
    }
}
