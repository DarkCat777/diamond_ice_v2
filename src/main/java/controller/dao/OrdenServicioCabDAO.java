package controller.dao;

import controller.hibernate.HibernateUtil;
import controller.hibernate.HibernateUtilException;
import model.entity.OrdenServicioCab;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import java.util.List;

public class OrdenServicioCabDAO extends AbstractDAO<Long, OrdenServicioCab> {
	/**
	 * Este metodo devuelve todos los registros de la tabla referidas por el tipo de
	 * dato "Data".
	 *
	 * @return Devuelve una lista de todos los registros.
	 * @throws HibernateUtilException    La excepcion lanzada es porque no se
	 *                                   configuro o no hay conexion a la base de
	 *                                   datos.
	 * @throws DataAccessObjectException La excepcion lanzada es porque no se
	 *                                   completo la operación de obtener todos los
	 *                                   registro de la tabla "Data".
	 * @see HibernateUtilException
	 * @see DataAccessObjectException
	 */
	@Override
	public List<OrdenServicioCab> getAll() throws HibernateUtilException, DataAccessObjectException {
		List<OrdenServicioCab> ordenServicioCabs;
		HibernateUtil.openSessionAndBindToThread();
		EntityManager entityManager = null;
		try {
			entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory()
					.createEntityManager();
			ordenServicioCabs = (List<OrdenServicioCab>) entityManager.createQuery("FROM OrdenServicioCab ")
					.getResultList();
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
		return ordenServicioCabs;
	}

	/**
	 * * Este metodo actualiza el registro de la base de datos.
	 *
	 * @param data Es el Objeto que actualizara el registro.
	 * @return Es la id del objeto que ha sido agregado a la base de datos.
	 * @throws DataAccessObjectException
	 * @see HibernateUtilException
	 * @see DataAccessObjectException
	 */

	public Long saveOrUpdate2(OrdenServicioCab data) throws DataAccessObjectException {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Long id = null;
			try {
				session.beginTransaction();
				id = (Long) session.save(data);
				session.getTransaction().commit();
			} catch (ConstraintViolationException ce) {
				/* entityManager.getTransaction().rollback(); */
				session.getTransaction().rollback();
				throw ce;
			} catch (Exception e) {
				session.getTransaction().rollback();
				// entityManager.getTransaction().rollback();
				throw e;
			} finally {
				if (session != null) {
					if (session.isConnected()) {
						session.close();
					}
				}
				HibernateUtil.closeSessionAndUnbindFromThread();
				return id;
			}
		} catch (HibernateException | HibernateUtilException he) {
			throw new DataAccessObjectException("No se pudo crear o actualizar");
		}
	}

	/**
	 * Este metodo obtiene los registros por su Id.
	 *
	 * @param aLong Es la Id del registro al que se quiere acceder.
	 * @return Retorna un Objeto del tipo "Data"
	 * @throws HibernateUtilException    La excepcion lanzada es porque no se
	 *                                   configuro o no hay conexion a la base de
	 *                                   datos.
	 * @throws DataAccessObjectException La excepcion lanzada es porque no se
	 *                                   completo la operación de obtener el
	 *                                   registro de la tabla "Data".
	 * @see HibernateUtilException
	 * @see DataAccessObjectException
	 */
	@Override
	public OrdenServicioCab get(Long aLong) throws HibernateUtilException, DataAccessObjectException {
		OrdenServicioCab ordenServicioCab;
		if (aLong == null) {
			throw new NullPointerException("La llave es nula");
		} else {
			HibernateUtil.openSessionAndBindToThread();
			EntityManager entityManager = null;
			try {
				entityManager = HibernateUtil.getSessionFactory().getCurrentSession().getEntityManagerFactory()
						.createEntityManager();
				ordenServicioCab = entityManager.find(OrdenServicioCab.class, aLong);
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
		return ordenServicioCab;
	}
}
