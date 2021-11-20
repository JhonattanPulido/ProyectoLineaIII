// Paquete
package co.edu.udec.controller;

// Librerías
import co.edu.udec.entity.Autor;
import org.springframework.http.HttpStatus;
import co.edu.udec.service.interfaz.ISAutor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import co.edu.udec.exception.ConflictException;
import co.edu.udec.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controlador de autores
 * @author Jhonattan Pulido
 * @since 19/11/2021
 * @version 1.0.0
 */
@Validated
@RestController
@RequestMapping("/autores")
public class AutoresController {
    
    // Variables

    /**
     * Métodos de la capa de servicios de autor
     */
    @Autowired
    private ISAutor serviciosAutor;

    /**
     * Constructor
     */
    public AutoresController() {

    }

    // Métodos

    /**
     * Crear autor
     * @param autor Datos del autor
     * @return 201 - CREATED
     * @throws ConflictException Ya existe un autor con un ORCID o e-mail
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> crear(@Validated @RequestBody Autor autor) throws    ConflictException {
        serviciosAutor.crear(autor);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    /**
     * Leer autor
     * @param orcid ORCID del autor
     * @param all Bandera para saber que datos retornar del autor
     * @return Datos del autor
     * @throws NotFoundException No se encontró el autor
     */
    @GetMapping(value = "/{orcid}/{all}", produces = "application/json")
    public ResponseEntity<Autor> leer(
        @PathVariable @Pattern(regexp = "[0-9]{8}", message = "El ORCID debe contener únicamente 8 números") String orcid,
        @PathVariable @NotNull(message = "Es requerido saber la información que se desea retornar") Boolean all
    ) throws    NotFoundException {
        return new ResponseEntity<Autor>(serviciosAutor.leer(orcid, all), HttpStatus.OK);
    }

}
