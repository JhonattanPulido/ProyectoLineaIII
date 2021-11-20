// Paquete
package co.edu.udec.repository.interfaz;

// Librerías
import co.edu.udec.entity.Autor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de la capa de datos de autor
 * @author Jhonattan Pulido
 * @since 19/11/2021
 * @version 1.0.0
 */
@Repository
public interface IDAutor extends JpaRepository<Autor, String> {
    
    // Métodos

    /**
     * Validar si un e-mail ya está en uso
     * @param email E-mail del autor
     * @return True: El e-mail ya está en uso | False: El e-mail no está en uso
     */
    public Boolean existsByEmail(String email);

}
