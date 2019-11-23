package controller.dao;

import controller.hibernate.HibernateUtilException;

import java.util.List;

/**
 * Esta interface es un modelo del patron de diseño DAO que se usara para el manejo de la entidades en la base de datos.
 *
 * @param <Data> Es el tipo de dato (Entidad) que recibira la Base de Datos.
 * @param <Id>   Es la llave (Id) con el cual obtendremos los datos de la Base de Datos
 * @author Erick David Carpio Hachiri
 * @version 1.0
 * @see HibernateUtilException
 * @see DataAccessObjectException
 */
public interface DataAccessObject<Id, Data> {
    /**
     * Este metodo devuelve todos los registros de la tabla referidas por el tipo de dato "Data".
     *
     * @return Devuelve una lista de todos los registros.
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de obtener todos los registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     */
    List<Data> getAll() throws HibernateUtilException, DataAccessObjectException;

    /**
     * Este metodo obtiene los registros por su Id.
     *
     * @param id Es la Id del registro al que se quiere acceder.
     * @return Retorna un Objeto del tipo "Data"
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de obtener el registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     */
    Data get(Id id) throws HibernateUtilException, DataAccessObjectException;

    /**
     * Este metodo actualiza el registro de la base de datos.
     *
     * @param data Es el Objeto que actualizara el registro.
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de guardar o actualizar el registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     * @return
     */
    void saveOrUpdate(Data data) throws HibernateUtilException, DataAccessObjectException;

    /**
     * Este metodo borra un registro de la base de datos.
     *
     * @param data Es el dato de que se borrara de la base de datos.
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de borrar el registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     */
    void delete(Data data) throws HibernateUtilException, DataAccessObjectException;

}