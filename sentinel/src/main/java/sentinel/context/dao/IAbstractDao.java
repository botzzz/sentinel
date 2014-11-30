package sentinel.context.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IAbstractDao<T> {

	/**
	 * Get the entity manager.
	 *
	 * @return the entity manager.
	 */
	public EntityManager getEntityManager();

	/**
	 * Persist the entity.
	 *
	 * @param entity
	 */
	public void persist(T entity);

	/**
	 * Remove the entity.
	 *
	 * @param entityId
	 *            the entity to remove.
	 */
	public void remove(Object entityId);

	/**
	 * Find the entity by its id.
	 *
	 * @param id
	 *            the id of the entity.
	 * @return the entity.
	 */
	public T find(Object id);

	/**
	 * Find all entities.
	 *
	 * @return all entities.
	 */
	public List<T> findAll();

	/**
	 * Find all entities from offset + limit.
	 *
	 * @return all entities.
	 */
	public List<T> findOffsetLimit(int offset, int limit);

	/**
	 * Count entities.
	 *
	 * @return the number of entities.
	 */
	public int count();

}
