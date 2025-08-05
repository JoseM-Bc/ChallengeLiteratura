# LiterAlura 📚

Catálogo de libros interactivo desarrollado en Java con Spring Boot, que permite consultar libros y autores desde la API pública de **Gutendex** y almacenarlos en una base de datos PostgreSQL.

## 🚀 Descripción

Este proyecto forma parte del desafío de backend del programa Oracle Next Education en colaboración con Alura Latam. La aplicación permite al usuario realizar búsquedas por título de libro, almacenar la información obtenida en una base de datos relacional y consultar distintos datos relacionados con libros y autores directamente desde la consola.

## 🔧 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Jackson (para deserialización de JSON)
- IntelliJ IDEA (Community Edition)
- Git & GitHub

## 🧠 Funcionalidades principales

✅ Buscar libro por título (consulta en la API y persistencia en base de datos)  
✅ Listar todos los libros almacenados  
✅ Listar todos los autores registrados  
✅ Consultar autores vivos en un año determinado  
✅ Listar cantidad de libros por idioma

## 🗃️ Estructura de la base de datos

Se usan dos entidades:

- `Libro`: contiene título, idioma, número de descargas y referencia a un autor.
- `Autor`: contiene nombre, año de nacimiento y año de fallecimiento.

Relación: **Muchos libros pueden tener un autor (OneToMany)**.

## 🔗 API utilizada

Se consume la API pública [Gutendex](https://gutendex.com/books/), basada en el Proyecto Gutenberg.

Ejemplo de búsqueda:
https://gutendex.com/books/?search=pride

## 🖥️ Ejecución

> Es una aplicación **de consola**. No incluye interfaz gráfica o frontend.

Para ejecutar:

1. Clona el repositorio.
2. Configura la conexión a PostgreSQL en `application.properties`.
3. Ejecuta la clase `LiteraluraApplication.java`.
4. Navega por el menú de opciones en consola.

## ⚙️ Configuración

En `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Asegúrate de tener PostgreSQL instalado, o puedes solo dejar esta configuración aunque no vayas a conectarte.

🧪 Recomendaciones
Usar Postman o navegador para probar respuestas de la API antes de mapearlas.

Validar entradas del usuario para evitar duplicados o errores en búsquedas.
