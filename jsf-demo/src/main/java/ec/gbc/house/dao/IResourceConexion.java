package ec.gbc.house.dao;

import ec.gbc.house.excepciones.SQLException;

import javax.persistence.EntityManager;
import java.util.List;

public interface IResourceConexion {
	
	/** 
	 * Obtiene la conexi&oacute;n de la base de datos
	 * @return
	 * @throws SQLException
	 */
	 public EntityManager getEntityManager() throws SQLException;
	 
	 /**
	  * Ingresa una entidad en la base de datos
	  * @param entidad
	  * @return
	  * @throws SQLException
	  */
	 public <T> T guardar(T entidad) throws SQLException;
	 
	 public <T> T buscarPorId(Class<T> clazz, Integer id) throws SQLException;

	 public <T> T actualizar(T entidad) throws SQLException;
	 
	 public <T> void borrar(T entidad) throws SQLException;
	 
	 public <T> List<T> buscarTodos(Class<T> clazz) throws SQLException;
	 

}
