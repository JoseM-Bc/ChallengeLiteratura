package comliteralura.repositorio;

import com.literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Derived query para autores vivos en un año dado (nacimiento <= año y (fallecimiento es null o fallecimiento >= año))
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoIsNullOrFechaFallecimientoGreaterThanEqual(int year1, int year2);
}
