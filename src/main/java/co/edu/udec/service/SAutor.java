// Paquete
package co.edu.udec.service;

// Librerías
import co.edu.udec.entity.Autor;
import co.edu.udec.service.interfaz.ISAutor;
import org.springframework.stereotype.Service;
import co.edu.udec.exception.ConflictException;
import co.edu.udec.exception.NotFoundException;
import co.edu.udec.repository.interfaz.IDAutor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Capa de servicios de autor
 * @author Jhonattan Pulido
 * @since 19/11/2021
 * @version 1.0.0
 */
@Service
public class SAutor implements ISAutor {
    
    // Variables

    /**
     * Métodos de la capa de datos de autor
     */
    @Autowired
    private IDAutor datosAutor;

    /**
     * Constructor
     */
    public SAutor() {

    }

    // Métodos

    /**
     * Crear autor
     * @param autor Datos del autor
     * @throws ConflictException Ya existe un autor con el ORCID o e-mail suministrado
     */
    @Override
    public void crear(Autor autor) throws   ConflictException {        
        
        // Validar si el ORCID ya existe
        if (datosAutor.existsById(autor.getOrcid()))
            throw new ConflictException("El ORCID: " + autor.getOrcid() + " ya está en uso");

        // Validar si el e-mail ya existe
        if (datosAutor.existsByEmail(autor.getEmail()))
            throw new ConflictException("El e-mail: " + autor.getEmail() + " ya está en uso");

        // Crear autor en la BD
        datosAutor.save(autor);

    }

    /**
     * Leer datos de un autor
     * @param orcid ORCID del autor
     * @param all True: Traer todos los datos asociados al autor | False: Traer únicamente los datos del autor
     * @return Datos del autor
     * @throws NotFoundException No se encontró el autor
     */
    @Override
    public Autor leer(String orcid, Boolean all) throws NotFoundException {
        
        // Leer los datos del autor filtrado por el ORCID
        Autor autor = datosAutor.getById(orcid);

        // Verificar si el autor si existe
        if (autor == null)
            throw new NotFoundException("No se encontró un autor con el ORCID: " + orcid);

        // Retornar los datos del autor
        return autor;
    }        

}
