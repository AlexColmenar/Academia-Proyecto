# Academia — Guía muy breve

- Ejecutar: `mvn -DskipTests package` && `java -jar target/academia.jar`

## Ejecutar
- Compilar: `mvn -DskipTests package`
- Ejecutar: `java -jar target/academia.jar`

## Base de datos
- Configuración JPA/Hibernate en `src/main/resources/META-INF/persistence.xml`.
- Si tu BD tiene la columna antigua de texto `Calificaciones.Materia`, ejecuta el script de migración antes de usar la nueva funcionalidad:
  - `db/migration/001_add_materias_and_migrate.sql`
- Haz backup antes de tocar la BD:
  - `mysqldump -u USUARIO -p -h HOST BASE_DATOS > backup_academia.sql`

## Entidades principales
- `Alumno`: id, nombre, email, carrera, lista `Calificacion`.
- `Materia`: id, nombre, descripcion.
- `Calificacion`: id, `Alumno` (ManyToOne), `Materia` (ManyToOne), nota, fecha.

## Vistas / componentes clave
- `MainMenu` — ventana principal con foto (`ImagenVista`) y botones.
- `AltaAlumnoPanel`, `ConsultaAlumnoPanel` — CRUD alumnos.
- `CalificacionPanel` — lista de calificaciones por alumno; añadir/editar usa `JComboBox` de materias.
- `MateriaPanel` — listado y gestión básica de materias.

 Entidades: `Alumno`, `Materia`, `Calificacion` (Alumno 1→N Calificacion; Materia 1→N Calificacion).
 UI: `MainMenu` (foto), CRUD alumnos, `CalificacionPanel` (selector de materias), `MateriaPanel` (gestión de materias).
 BD: migración en `db/migration/001_add_materias_and_migrate.sql` — haz backup antes.
 Comportamientos clave: no eliminar `Materia` con calificaciones; crear materias inline desde el diálogo de calificaciones.

- Añadir y gestionar calificaciones por alumno
- Consultar y editar registros desde la interfaz
- Generar reportes y estadísticas desde la aplicación

**Tecnologías**
- Java, Maven, Hibernate/JPA, Swing, MySQL (JDBC)

**Autor**
Alejandro Colmenar
