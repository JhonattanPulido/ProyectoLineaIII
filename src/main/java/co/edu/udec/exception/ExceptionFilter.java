// Paquete
package co.edu.udec.exception;

// Librerías
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Filtro de excepciones
 * @author Jhonattan Pulido
 * @since 19/11/2021
 * @version 1.0.0
 */
@RestController
@ControllerAdvice
public class ExceptionFilter extends ResponseEntityExceptionHandler {
    
    // Métodos

    /**
     * Filtro de NOT FOUND EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 404 - NOT FOUND
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionDTO> onNotFoundException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.NOT_FOUND, ex, request);
    }

    /**
     * Filtro de CONFLICT EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 409 - CONFLICT
     */
    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<ExceptionDTO> onConflictException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.CONFLICT, ex, request);
    }

    /**
     * Filtro de EXCEPTION
     * @param ex Datos de la excepción
     * @param request Datos de la petición
     * @return 500 - INTERNAL SERVER ERROR
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionDTO> onException(Exception ex, WebRequest request) {
        return responseError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    /**
     * Retornar objeto de error
     * @param httpStatus Código de error
     * @param ex Excepción generada
     * @param request Datos de la petición
     * @return Objeto de error cargado
     */
    private ResponseEntity<ExceptionDTO> responseError(HttpStatus httpStatus, Exception ex, WebRequest request) {

        // Generando objeto de error
        ExceptionDTO exceptionDTO = new ExceptionDTO(
            httpStatus.value(),
            httpStatus.toString(),
            ex.getMessage(),
            request.getDescription(false)
        );

        // Retornando respuesta cargada
        return new ResponseEntity<ExceptionDTO>(exceptionDTO, httpStatus);

    }

}
