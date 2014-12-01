package opl.sentinel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Abstract data access object.
 * 
 * @author Quentin
 * @param <T>
 *            the entity type to be managed.
 */
public abstract class AbstractDao<T> implements IAbstractDao<T> {

	/**
	 * Entity manager factory.
	 */
	private EntityManagerFactory emfactory;

	/**
	 * The entity class.
	 */
	private Class<T> entityClass;

	/**
	 * Constructor.
	 * 
	 * @param entityClass
	 */
	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
		this.emfactory = Persistence.createEntityManagerFactory("sentinelPU");
	}

	/**
	 * {@inheritDoc}
	 */
	public EntityManager getEntityManager() {
		return this.emfactory.createEntityManager();
	}

	/**
	 * {@inheritDoc}
	 */
	public void persist(T entity) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(Object entityId) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		T entity = find(entityId);
		if (entity != null) {
			getEntityManager().remove(entity);
		}
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> findAll() {
		EntityManager entityManager = getEntityManager();
		CriteriaQuery<T> cq = entityManager.getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return entityManager.createQuery(cq).getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> findOffsetLimit(int offset, int limit) {
		EntityManager entityManager = getEntityManager();
		CriteriaQuery<T> cq = entityManager.getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		TypedQuery<T> typedQuery = entityManager.createQuery(cq)
				.setFirstResult(offset).setMaxResults(limit);
		return typedQuery.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	public int count() {
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> root = cq.from(entityClass);
		cq.select(cb.count(root));
		return entityManager.createQuery(cq).getSingleResult().intValue();
	}
}