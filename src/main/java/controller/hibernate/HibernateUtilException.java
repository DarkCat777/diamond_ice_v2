package controller.hibernate;

/**
 * Esta clase es una excepción la cual es lanzada cuando no se puede configurar la conexion a la base de datos o no se
 * puede hacer conexión con la base de datos.
 *
 * @see HibernateUtil
 */

public class HibernateUtilException extends Exception {
    /**
     * Este es el constructo que recibe un string el cual es el causante del error.
     *
     * @param msg es el mensaje de error que se escribira en pantalla.}
     * @see HibernateUtil
     */
    public HibernateUtilException(String msg) {
        super(msg);
    }
}
