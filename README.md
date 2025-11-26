# 📚 Sistema de Gestión Académica

Sistema de gestión académica desarrollado con Java, Hibernate/JPA y Swing. Permite gestionar alumnos, calificaciones y generar reportes académicos.

## 🏗️ Estructura del Proyecto

El proyecto sigue el patrón **Modelo-Vista-Controlador (MVC)**:

### 📁 Modelo (`org.example.model`)
- **`Alumno.java`** - Entidad que representa un estudiante
- **`Calificacion.java`** - Entidad que representa una calificación

### 📁 DAO (`org.example.dao`)  
- **`AlumnoDAO.java`** - Operaciones CRUD para alumnos
- **`CalificacionDAO.java`** - Operaciones CRUD para calificaciones

### 📁 Vista (`org.example.view`)
- **`MainMenu.java`** - Menú principal del sistema
- **`AltaAlumnoPanel.java`** - Ventana para registrar alumnos
- **`AltaCalificacionPanel.java`** - Ventana para registrar calificaciones
- **`ConsultaAlumnoPanel.java`** - Ventana para gestionar alumnos
- **`ConsultaCalificacionPanel.java`** - Ventana para gestionar calificaciones
- **`ReportePanel.java`** - Ventana para reportes y estadísticas

### 📁 Controlador (`org.example.controller`)
- **`AcademiaController.java`** - Controlador principal que conecta vistas con DAOs

## 🗄️ Base de Datos

### Tabla `alumnos`
```sql
CREATE TABLE alumnos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    carrera VARCHAR(255) NOT NULL
);
```

### Tabla `calificaciones`
```sql
CREATE TABLE calificaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    materia VARCHAR(255) NOT NULL,
    nota DOUBLE NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (estudiante_id) REFERENCES alumnos(id)
);
```

### Relación 1:N
- Un **alumno** puede tener **muchas calificaciones**
- Una **calificación** pertenece a **un alumno**

## 🚀 Funcionalidades

### ✅ Gestión de Alumnos
- Registrar nuevos alumnos (nombre, email, carrera)
- Buscar alumnos por nombre o carrera
- Listar todos los alumnos
- Editar información de alumnos
- Eliminar alumnos

### 📊 Gestión de Calificaciones
- Registrar calificaciones (alumno, materia, nota, fecha)
- Buscar calificaciones por alumno, materia o nota mínima
- Listar todas las calificaciones
- Editar calificaciones existentes
- Eliminar calificaciones
- Calcular promedio por alumno

### 📈 Reportes y Estadísticas
- Promedio general de todos los alumnos
- Alumnos destacados (con mejores calificaciones)
- Materias con mejor rendimiento
- Listado completo de calificaciones
- Alumnos que requieren apoyo académico

## ⚙️ Tecnologías Utilizadas

- **Java 24** - Lenguaje de programación
- **Maven** - Gestión de dependencias
- **Hibernate 6.6.1** - ORM (Object-Relational Mapping)
- **Jakarta Persistence API 3.1** - Especificación JPA
- **MySQL Connector 9.4.0** - Driver de base de datos
- **Swing** - Interfaz gráfica de usuario
- **SLF4J** - Logging

## 🔧 Configuración

### Requisitos
- Java 24 o superior
- Maven 3.8+
- MySQL/MariaDB
- IDE compatible (IntelliJ IDEA, Eclipse, VS Code)

### Configuración de Base de Datos
1. Edita el archivo `src/main/resources/META-INF/persistence.xml`
2. Modifica las propiedades de conexión:
   ```xml
   <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/tu_base_datos"/>
   <property name="jakarta.persistence.jdbc.user" value="tu_usuario"/>
   <property name="jakarta.persistence.jdbc.password" value="tu_contraseña"/>
   ```

### Compilación y Ejecución
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn exec:java -Dexec.mainClass="org.example.Main"

# Generar JAR ejecutable
mvn clean package
```

## 📋 Uso del Sistema

1. **Inicio**: Ejecutar la clase `Main.java`
2. **Registro de Alumnos**: Usar "Registrar Alumno" para añadir estudiantes
3. **Registro de Calificaciones**: Usar "Registrar Calificación" para añadir notas
4. **Consultas**: Usar las opciones de gestión para buscar y modificar datos
5. **Reportes**: Generar estadísticas y reportes académicos

## 🎨 Características de la Interfaz

- **Diseño Moderno**: Interfaz colorida y fácil de usar
- **Navegación Intuitiva**: Botones claramente identificados con iconos
- **Validaciones**: Verificación de datos en tiempo real
- **Feedback Visual**: Mensajes de confirmación y error
- **Tablas Interactivas**: Visualización organizada de datos

## 🔗 Relación con Proyecto Concesionario

Este proyecto está basado en la estructura del sistema de **concesionario-master**, adaptado para el contexto académico:

- **Similar**: Patrón MVC, uso de Hibernate/JPA, Maven, Swing
- **Diferente**: Entidades académicas, relaciones 1:N, funcionalidades específicas de educación

## 👥 Autor

Desarrollado como sistema académico de gestión para instituciones educativas.

---

💡 **Tip**: Para mejores resultados, asegúrate de que la base de datos esté configurada correctamente antes de ejecutar la aplicación.
