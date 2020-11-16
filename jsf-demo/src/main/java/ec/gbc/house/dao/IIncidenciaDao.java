package ec.gbc.house.dao;

import ec.gbc.house.entidades.Empleado;
import ec.gbc.house.entidades.Incidencia;
import ec.gbc.house.enumeraciones.EstadoIncidencia;
import ec.gbc.house.enumeraciones.EstadoRegistro;
import ec.gbc.house.excepciones.SQLException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IIncidenciaDao extends IResourceConexion{

    List<Incidencia> buscarIncidenciasPorEstado(EstadoIncidencia estadoIncidencia) throws SQLException;

    List<Incidencia> buscarIncidencias() throws SQLException;
}
