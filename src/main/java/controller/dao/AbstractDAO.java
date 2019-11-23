package controller.dao;

import controller.hibernate.HibernateUtil;
import controller.hibernate.HibernateUtilException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDAO<Id extends Serializable, Data> implements DataAccessObject<Id, Data> {
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
	public abstract List<Data> getAll() throws HibernateUtilException, DataAccessObjectException;

	/**
	 * Este metodo obtiene los registros por su Id.
	 *
	 * @param id Es la Id del registro al que se quiere acceder.
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
	public abstract Data get(Id id) throws HibernateUtilException, DataAccessObjectException;

	/**
	 * Este metodo actualiza el registro de la base de datos.
	 *
	 * @param data Es el Objeto que actualizara el registro.
	 * @throws HibernateUtilException    La excepcion lanzada es porque no se
	 *                                   configuro o no hay conexion a la base de
	 *                                   datos.
	 * @throws DataAccessObjectException La excepcion lanzada es porque no se
	 *                                   completo la operación de guardar o
	 *                                   actualizar el registro de la tabla "Data".
	 * @see HibernateUtilException
	 * @see DataAccessObjectException
	 */
	@Override
	public void saveOrUpdate(Data data) throws HibernateUtilException, DataAccessObjectException {
		if (data == null) {
			throw new NullPointerException("El objeto access es nulo");
		} else {
			HibernateUtil.openSessionAndBindToThread();
			try {
				EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
				try {
					entityManager.getTransaction().begin();
					entityManager.merge(data);
					entityManager.getTransaction().commit();
				} catch (ConstraintViolationException ce) {
					entityManager.getTransaction().rollback();
					throw ce;
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					throw e;
				} finally {
					if (entityManager != null) {
						if (entityManager.isOpen()) {
							entityManager.close();
						}
					}
					HibernateUtil.closeSessionAndUnbindFromThread();
				}
			} catch (HibernateException he) {
				throw new DataAccessObjectException("No se pudo crear o actualizar");
			}
		}
	}

	/**
	 * Este metodo borra un registro de la base de datos.
	 *
	 * @param data Es el dato de que se borrara de la base de datos.
	 * @throws HibernateUtilException    La excepcion lanzada es porque no se
	 *                                   configuro o no hay conexion a la base de
	 *                                   datos.
	 * @throws DataAccessObjectException La excepcion lanzada es porque no se
	 *                                   completo la operación de borrar el registro
	 *                                   de la tabla "Data".
	 * @see HibernateUtilException
	 * @see DataAccessObjectException
	 */
	public void delete(Data data) throws HibernateUtilException, DataAccessObjectException {
		if (data == null) {
			throw new NullPointerException("El objeto access es nulo");
		} else {
			HibernateUtil.openSessionAndBindToThread();
			try {
				EntityManager entityManager = HibernateUtil.getSessionFactory().getCurrentSession()
						.getEntityManagerFactory().createEntityManager();
				try {
					entityManager.getTransaction().begin();
					entityManager.remove(data);
					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					throw e;
				} finally {
					if (entityManager != null) {
						if (entityManager.isOpen()) {
							entityManager.close();
						}
					}
					HibernateUtil.closeSessionAndUnbindFromThread();
				}
			} catch (HibernateException he) {
				throw new DataAccessObjectException("No se pudo crear o actualizar");
			}
		}
	}
}
