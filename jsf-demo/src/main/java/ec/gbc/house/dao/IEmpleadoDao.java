package ec.gbc.house.dao;

import ec.gbc.house.entidades.Empleado;
import ec.gbc.house.enumeraciones.EstadoRegistro;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IEmpleadoDao extends IResourceConexion{

    List<Empleado> buscarEmpleadosPorEstadoAndUsername(EstadoRegistro estado, String username);
}
