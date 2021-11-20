// Paquete
package co.edu.udec.service.interfaz;

import co.edu.udec.exception.ConflictException;
import co.edu.udec.exception.NotFoundException;

/**
 * Interfaz genérica de la capa de servicios de CRUD
 * @author Jhonattan Pulido
 * @since 19/11/2021
 * @version 1.0.0
 * @param T Clase de la entidad
 * @param ID Clase del tipo de dato del ID de la entidad
 */
public interface ISCRUD<T, ID> {
    
    /**
     * Crear entidad
     * @param t Datos de la entidad
     * @throws ConflictException Un ID o campo único se repite
     */
    public void crear(T t) throws   ConflictException;

    /**
     * Leer los datos de un registro
     * @param id ID del registro
     * @param all True: Traer todos los datos asociados al registro | False: Traer únicamente los datos del registro
     * @return Datos del registro
     * @throws NotFoundException No se encontró el registro con el ID especificado
     */
    public T leer(ID id, Boolean all) throws    NotFoundException;

}
