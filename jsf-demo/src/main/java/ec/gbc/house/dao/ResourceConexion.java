package ec.gbc.house.dao;

import ec.gbc.house.excepciones.SQLException;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class ResourceConexion implements IResourceConexion {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() throws SQLException {
		if (this.entityManager == null)
			throw new SQLException("Entity Manager no se ha inicializado");
		return this.entityManager;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public <T> T guardar(T entidad) throws SQLException {
		this.getEntityManager().persist(entidad);
		return entidad;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public <T> T actualizar(T entidad) throws SQLException {
		this.getEntityManager().merge(entidad);
		return entidad;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public <T> void borrar(T entidad) throws SQLException {
		this.getEntityManager().remove(entidad);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public <T> T buscarPorId(Class<T> clazz, Integer id) throws SQLException {
		T entidad = (T) this.getEntityManager().find(clazz, id);
		return entidad;
	}

	
	@Override
	public <T> List<T> buscarTodos(Class<T> clazz) throws SQLException {
		try{
			CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(clazz);
			cq.from(clazz);
			return (List<T>) this.entityManager.createQuery(cq).getResultList();
		}catch(NoResultException e){
			return null;
		}
	}

}
