package comliteralura;

import comliteralura.modelo.Autor;
import comliteralura.modelo.Libro;
import comliteralura.repositorio.AutorRepository;
import comliteralura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mostrarMenu();
    }

    private void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n===== LiterAlura - Catálogo de Libros =====");
            System.out.println("1. Buscar libro por título y agregar a base de datos");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Listar autores");
            System.out.println("4. Listar autores vivos en un año específico");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Mostrar cantidad de libros por idioma");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> buscarYAgregarLibro();
                case "2" -> listarLibros();
                case "3" -> listarAutores();
                case "4" -> listarAutoresVivosEnAno();
                case "5" -> listarLibrosPorIdioma();
                case "6" -> mostrarCantidadLibrosPorIdioma();
                case "0" -> {
                    System.out.println("Saliendo... ¡Hasta luego!");
                    continuar = false;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void buscarYAgregarLibro() {
        System.out.print("Ingrese el título del libro a buscar: ");
        String tituloBuscado = scanner.nextLine().trim();

        // Validación simple
        if (tituloBuscado.isEmpty()) {
            System.out.println("El título no puede estar vacío.");
            return;
        }

        // Verificar si libro ya existe
        if (libroRepository.existsByTituloIgnoreCase(tituloBuscado)) {
            System.out.println("El libro ya está registrado en la base de datos.");
            return;
        }

        // Aquí se simula la llamada a la API Gutendex (a implementar)
        // Por ejemplo, llamar método que use HttpClient y ObjectMapper para obtener datos
        // Para este ejemplo, crearemos un libro y autor ficticio para simular:

        // Simulación - reemplazar con llamada real a API
        Autor autor = new Autor("Jane Austen", 1775, 1817);
        Libro libro = new Libro("Pride and Prejudice", "en", 6493, autor);

        // Guardar en base de datos
        autorRepository.save(autor);  // por cascada también se guarda con libro, pero es seguro guardarlo primero
        libroRepository.save(libro);

        System.out.println("Libro agregado correctamente:");
        System.out.println(libro);
    }

    private void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("\nLibros registrados:");
            libros.forEach(libro -> System.out.printf("- %s (Idioma: %s, Descargas: %d) Autor: %s%n",
                    libro.getTitulo(), libro.getIdioma(), libro.getNumeroDescargas(), libro.getAutor().getNombre()));
        }
    }

    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("\nAutores registrados:");
            autores.forEach(autor -> System.out.printf("- %s (Nacimiento: %d, Fallecimiento: %s)%n",
                    autor.getNombre(),
                    autor.getFechaNacimiento(),
                    autor.getFechaFallecimiento() == null ? "N/A" : autor.getFechaFallecimiento()));
        }
    }

    private void listarAutoresVivosEnAno() {
        System.out.print("Ingrese el año para buscar autores vivos: ");
        String inputAno = scanner.nextLine();

        int ano;
        try {
            ano = Integer.parseInt(inputAno);
        } catch (NumberFormatException e) {
            System.out.println("Año inválido. Debe ser un número entero.");
            return;
        }

        // Buscar autores vivos en el año
        List<Autor> autoresVivos = autorRepository.findByFechaNacimientoLessThanEqualAndFechaFallecimientoIsNullOrFechaFallecimientoGreaterThanEqual(ano, ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año indicado.");
        } else {
            System.out.println("\nAutores vivos en el año " + ano + ":");
            autoresVivos.forEach(autor -> System.out.printf("- %s (Nacimiento: %d, Fallecimiento: %s)%n",
                    autor.getNombre(),
                    autor.getFechaNacimiento(),
                    autor.getFechaFallecimiento() == null ? "N/A" : autor.getFechaFallecimiento()));
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el código del idioma (ej. en, es, fr, pt): ");
        String idioma = scanner.nextLine().trim().toLowerCase();

        if (idioma.isEmpty()) {
            System.out.println("El idioma no puede estar vacío.");
            return;
        }

        List<Libro> libros = libroRepository.findByIdiomaIgnoreCase(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
        } else {
            System.out.println("\nLibros en idioma '" + idioma + "':");
            libros.forEach(libro -> System.out.printf("- %s (Autor: %s)%n", libro.getTitulo(), libro.getAutor().getNombre()));
        }
    }

    private void mostrarCantidadLibrosPorIdioma() {
        System.out.print("Ingrese el código del idioma para contar libros: ");
        String idioma = scanner.nextLine().trim().toLowerCase();

        if (idioma.isEmpty()) {
            System.out.println("El idioma no puede estar vacío.");
            return;
        }

        int cantidad = libroRepository.countByIdiomaIgnoreCase(idioma);
        System.out.println("Cantidad de libros en idioma '" + idioma + "': " + cantidad);
    }
}
