package controller.dao;

import controller.hibernate.HibernateUtil;
import controller.hibernate.HibernateUtilException;
import model.entity.Empleado;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmpleadoDAO extends AbstractDAO<Long, Empleado> {
    /**
     * Este metodo devuelve todos los registros de la tabla referidas por el tipo de dato "Data".
     *
     * @return Devuelve una lista de todos los registros.
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de obtener todos los registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     */
    @Override
    public List<Empleado> getAll() throws HibernateUtilException, DataAccessObjectException {
        List<Empleado> empleados;
        HibernateUtil.openSessionAndBindToThread();
        EntityManager entityManager = null;
        try {
            entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory().createEntityManager();
            empleados = (List<Empleado>) entityManager.createQuery("FROM Empleado ").getResultList();
        } catch (HibernateException he) {
            throw new DataAccessObjectException("No se obtener los resultados");
        } finally {
            if (entityManager != null) {
                if (entityManager.isOpen()) {
                    entityManager.close();
                }
            }
        }
        return empleados;
    }

    /**
     * Este metodo obtiene los registros por su Id.
     *
     * @param aLong Es la Id del registro al que se quiere acceder.
     * @return Retorna un Objeto del tipo "Data"
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de obtener el registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     */
    @Override
    public Empleado get(Long aLong) throws HibernateUtilException, DataAccessObjectException {
        Empleado empleado;
        if (aLong == null) {
            throw new NullPointerException("La llave es nula");
        } else {
            HibernateUtil.openSessionAndBindToThread();
            EntityManager entityManager = null;
            try {
                entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory().createEntityManager();
                empleado = entityManager.find(Empleado.class, aLong);
            } catch (HibernateException he) {
                throw new DataAccessObjectException("No se pudo crear o actualizar");
            } finally {
                if (entityManager != null) {
                    if (entityManager.isOpen()) {
                        entityManager.close();
                    }
                }
                HibernateUtil.closeSessionAndUnbindFromThread();
            }

        }
        return empleado;
    }

    public Empleado get_ByCorreo_ByPassword(String correo, String password) throws HibernateUtilException, DataAccessObjectException {
        Empleado empleado;
        if (correo == null || password == null) {
            throw new NullPointerException("Los datos de correo o contraseña son nulos.");
        } else if (correo.equals(" ") || password.equals(" ")) {
            throw new NullPointerException("Los datos de correo o contraseña son vacios.");
        } else {
            HibernateUtil.openSessionAndBindToThread();
            EntityManager entityManager = null;
            try {
                entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory().createEntityManager();
                TypedQuery<Empleado> usuariosLogin = entityManager.createNamedQuery(Empleado.LOGIN, Empleado.class);
                usuariosLogin.setParameter("correoEmpleado", correo);
                usuariosLogin.setParameter("passwordEmpleado", password);
                empleado = usuariosLogin.getSingleResult();
            } catch (HibernateException he) {
                throw new DataAccessObjectException("No se pudo crear o actualizar");
            } finally {
                if (entityManager != null) {
                    if (entityManager.isOpen()) {
                        entityManager.close();
                    }
                }
                HibernateUtil.closeSessionAndUnbindFromThread();
            }
        }
        return empleado;
    }
}
