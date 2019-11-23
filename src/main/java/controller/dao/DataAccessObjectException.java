package controller.dao;

/**
 * Esta clase es una excepcion que se lanza cuando alguna operacion de DAO no se llega a ejecutar de manera correcta.
 *
 * @see DataAccessObject
 */
public class DataAccessObjectException extends Exception {
    /**
     * Este es el constructor de la excepción.
     *
     * @param msg Es el mensaje que envia el DAO en cada uno de sus metodos
     * @see DataAccessObject
     */
    public DataAccessObjectException(String msg) {
        super(msg);
    }
}
