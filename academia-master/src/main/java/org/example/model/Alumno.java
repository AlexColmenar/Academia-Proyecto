package org.example.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Estudiantes")
public class Alumno {
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

    // Relación uno a muchos: un alumno tiene muchas calificaciones
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Calificacion> calificaciones;

    public Alumno() {
    }

    public Alumno(String nombre, String email, String carrera) {
        this.nombre = nombre;
        this.email = email;
        this.carrera = carrera;
    }

    // getters y setters
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
}
