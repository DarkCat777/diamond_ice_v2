package controller.dao;

import controller.hibernate.HibernateUtil;
import controller.hibernate.HibernateUtilException;
import model.entity.OrdenServicioDet;
import model.id.OrdenServicioDetPK;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdenServicioDetDAO extends AbstractDAO<OrdenServicioDetPK, OrdenServicioDet> {
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
    public List<OrdenServicioDet> getAll() throws HibernateUtilException, DataAccessObjectException {
        List<OrdenServicioDet> ordenServicioDets;
        HibernateUtil.openSessionAndBindToThread();
        EntityManager entityManager = null;
        try {
            entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory().createEntityManager();
            ordenServicioDets = (List<OrdenServicioDet>) entityManager.createQuery("FROM OrdenServicioDet ").getResultList();
        } catch (HibernateException he) {
            throw new DataAccessObjectException("No se obtener los resultados");
        } finally {
            if (entityManager != null) {
                if (entityManager.isOpen()) {
                    entityManager.close();
                }
            }
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        return ordenServicioDets;
    }

    /**
     * Este metodo obtiene los registros por su Id.
     *
     * @param ordenServicioDetPK Es la Id del registro al que se quiere acceder.
     * @return Retorna un Objeto del tipo "Data"
     * @throws HibernateUtilException    La excepcion lanzada es porque no se configuro o no hay conexion a la base de datos.
     * @throws DataAccessObjectException La excepcion lanzada es porque no se completo la operación de obtener el registro de la tabla "Data".
     * @see HibernateUtilException
     * @see DataAccessObjectException
     */
    @Override
    public OrdenServicioDet get(OrdenServicioDetPK ordenServicioDetPK) throws HibernateUtilException, DataAccessObjectException {
        OrdenServicioDet ordenServicioDet;
        if (ordenServicioDetPK == null) {
            throw new NullPointerException("La llave es nula");
        } else {
            HibernateUtil.openSessionAndBindToThread();
            EntityManager entityManager = null;
            try {
                entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory().createEntityManager();
                ordenServicioDet = entityManager.find(OrdenServicioDet.class, ordenServicioDetPK);
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
        return ordenServicioDet;
    }
}
