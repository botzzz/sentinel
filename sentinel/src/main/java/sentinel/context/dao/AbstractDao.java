package sentinel.context.dao;

import java.util.List;

import javax.persistence.EntityManager;
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
	 * Entity manager.
	 */
	private EntityManager em;

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
		this.em = Persistence.createEntityManagerFactory("sentinelPU")
				.createEntityManager();
	}

	/**
	 * {@inheritDoc}
	 */
	public EntityManager getEntityManager() {
		return this.em;
	}

	/**
	 * {@inheritDoc}
	 */
	public void persist(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(Object entityId) {
		getEntityManager().getTransaction().begin();
		T entity = find(entityId);
		if (entity != null) {
			getEntityManager().remove(entity);
		}
		getEntityManager().getTransaction().commit();
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
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> findOffsetLimit(int offset, int limit) {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		TypedQuery<T> typedQuery = getEntityManager().createQuery(cq)
				.setFirstResult(offset).setMaxResults(limit);
		return typedQuery.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	public int count() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> root = cq.from(entityClass);
		cq.select(cb.count(root));
		return getEntityManager().createQuery(cq).getSingleResult().intValue();
	}
}