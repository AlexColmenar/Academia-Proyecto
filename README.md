# Sistema de Gestión Académica

Aplicación Java (Hibernate/JPA + Swing) para gestionar alumnos y calificaciones. Implementa el patrón MVC y persiste datos en una base de datos relacional.

**Estructura principal**
- `org.example.model`: entidades (`Alumno`, `Calificacion`)
- `org.example.dao`: acceso a datos (DAOs)
- `org.example.view`: interfaces Swing
- `org.example.controller`: lógica de control

**Requisitos**
- Java 24 o superior
- Maven 3.8+
- MySQL/MariaDB o similar (driver JDBC)

**Configuración**
Edita `src/main/resources/META-INF/persistence.xml` y ajusta `jakarta.persistence.jdbc.url`, `jakarta.persistence.jdbc.user` y `jakarta.persistence.jdbc.password`.

**Compilar y ejecutar**
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.Main"
# Para empaquetar: mvn clean package
```

**Uso rápido**
- Registrar y gestionar alumnos
- Añadir y gestionar calificaciones por alumno
- Consultar y editar registros desde la interfaz
- Generar reportes y estadísticas desde la aplicación

**Tecnologías**
- Java, Maven, Hibernate/JPA, Swing, MySQL (JDBC)

**Autor**
Alejandro Colmenar
