package controller.hibernate;

import model.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

/**
 * Esta clase utliza el patron Singleton, para inicializar una solo instancia de lo conexion a la base de datos con PostgreSQL.
 * Ademas se especializa en inicializar una por hilo con los metodos que se ven en la clase.
 *
 * @author Erick David Carpio Hachiri
 * @version Version 1.0
 * @see <a href="http://www.cursohibernate.es/doku.php?id=unidades:07_arquitectura:01_hibernateutil"></a>
 */

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Este metodo carga el archivo de configuración "controller.hibernate.cfg.xml" e inicializa una nueva Fabrica de Sesiones (Patron Factory) de conexion.
     *
     * @throws HibernateUtilException La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     */
    public static synchronized void buildSessionFactory() throws HibernateUtilException {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            try {
                //configuration.configure("/controller.hibernate.cfg.xml");
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "org.postgresql.Driver");
                properties.put(Environment.URL, "jdbc:postgresql://ec2-54-235-104-136.compute-1.amazonaws.com:5432/d9tu6tevc25pg7");
                //properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/test");
                properties.put(Environment.USER, "xbaviczpnbkrzi");
                //properties.put(Environment.USER, "postgres");
                properties.put(Environment.PASS, "35c6b2d1a5e3f58774a887c65476cf0be1aae2e36af8070a86b99cfc02a81769");
                //properties.put(Environment.PASS, "123456");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");
                properties.put(Environment.SHOW_SQL, "false");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(properties);
                /*Agregar las clases que seran mapeadas en la base de datos*/
                configuration.addAnnotatedClass(Access.class);
                configuration.addAnnotatedClass(Cliente.class);
                configuration.addAnnotatedClass(Empleado.class);
                configuration.addAnnotatedClass(Insumo.class);
                configuration.addAnnotatedClass(OrdenServicioCab.class);
                configuration.addAnnotatedClass(OrdenServicioDet.class);
                configuration.addAnnotatedClass(Recurso.class);
                configuration.addAnnotatedClass(Rol.class);
                configuration.addAnnotatedClass(Servicio.class);
                configuration.addAnnotatedClass(TipoInsumo.class);
                configuration.addAnnotatedClass(TipoPrenda.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException he) {
                he.printStackTrace();
                throw new HibernateUtilException("No se ha podido configurar la base de datos, o no tiene conexion a la misma");
            }
        }
    }

    /**
     * Este metodo abre una sesión de conexión a la base de datos para el hilo actual.
     */

    public static void openSessionAndBindToThread() {
        Session session = sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }

    /**
     * Este metodo recupera la sesion factory
     *
     * @return retorna un objeto de tipo SesionFactory
     * @throws HibernateUtilException La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     */
    public static SessionFactory getSessionFactory() throws HibernateUtilException {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Este metodo cierra la sesion del hilo en el que se encuentra.
     */
    public static void closeSessionAndUnbindFromThread() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        if (session != null) {
            session.close();
        }
    }

    /**
     * Este metodo cierra la sesion factory creada.
     */
    public static void closeSessionFactory() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
        }
    }
}