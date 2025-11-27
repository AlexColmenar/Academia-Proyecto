package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Calificaciones")
public class Calificacion {
    // Entidad `Calificacion` mapeada con JPA.
    // Representa una nota de una materia para un `Alumno` en una fecha.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Materia")
    private String materia;

    @Column(name = "Nota")
    private Double nota;

    @Column(name = "Fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "Estudiante_id")
    private Alumno alumno;

    public Calificacion() {
    }

    public Calificacion(String materia, Double nota, LocalDate fecha, Alumno alumno) {
        this.materia = materia;
        this.nota = nota;
        this.fecha = fecha;
        this.alumno = alumno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
