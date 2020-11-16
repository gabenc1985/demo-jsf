package ec.gbc.house.servicios;

import ec.gbc.house.dao.IEmpleadoDao;
import ec.gbc.house.dto.EmpleadoDTO;
import ec.gbc.house.entidades.Empleado;
import ec.gbc.house.enumeraciones.EstadoRegistro;
import ec.gbc.house.excepciones.SQLException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@LocalBean
@Stateless
public class AdministracionEmpleado {

    @EJB
    IEmpleadoDao empleadoDao;

    public List<EmpleadoDTO> buscarEmpleados() throws SQLException {
        List<Empleado> lista = this.empleadoDao.buscarTodos(Empleado.class);
        return getEmpleadoDTOS(lista);
    }

    public void guardarEmpleado(String username, String nombre, String apellido) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setUsername(username);
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setFechaCreacion(new Date());
        empleado.setEstado(EstadoRegistro.ACTIVO);
        this.empleadoDao.guardar(empleado);
    }

    public List<EmpleadoDTO> buscarEmpleadosPorUsername(String username) {
        List<Empleado> lista = this.empleadoDao.buscarEmpleadosPorEstadoAndUsername(EstadoRegistro.ACTIVO, username);
        return getEmpleadoDTOS(lista);
    }

    private List<EmpleadoDTO> getEmpleadoDTOS(List<Empleado> lista) {
        List<EmpleadoDTO> empleados = lista.stream().map(new Function<Empleado, EmpleadoDTO>() {
            @Override
            public EmpleadoDTO apply(Empleado entidad) {
                return new EmpleadoDTO(entidad.getId(), entidad.getUsername(), entidad.getNombre(), entidad.getApellido());

            }
        }).collect(Collectors.toList());

        return empleados;
    }
}
