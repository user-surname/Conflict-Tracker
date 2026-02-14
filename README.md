# Conflict Tracker

Conflict Tracker és una aplicació per gestionar conflictes amb informació detallada com nom, descripció i data de creació. Permet consultar conflictes a través de endpoints RESTful.

## Requisits previs

- Java 17 o superior
- Maven 3.x
- PostgreSQL (o altra base de dades suportada)
- IDE recomanat: IntelliJ IDEA o VS Code

## Configuració

1. Configura la base de dades al fitxer `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/conflictdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update