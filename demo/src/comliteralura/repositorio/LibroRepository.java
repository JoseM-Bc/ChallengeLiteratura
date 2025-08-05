package comliteralura.repositorio;

import com.literalura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libro por título exacto
    boolean existsByTituloIgnoreCase(String titulo);

    // Buscar libros por idioma
    List<Libro> findByIdiomaIgnoreCase(String idioma);

    // Contar libros por idioma
    int countByIdiomaIgnoreCase(String idioma);
}
