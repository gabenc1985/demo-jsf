package ec.gbc.house.servicios;

import ec.gbc.house.dao.IIncidenciaDao;
import ec.gbc.house.dto.IncidenciaDTO;
import ec.gbc.house.entidades.Empleado;
import ec.gbc.house.entidades.Incidencia;
import ec.gbc.house.enumeraciones.EstadoIncidencia;
import ec.gbc.house.enumeraciones.EstadoRegistro;
import ec.gbc.house.excepciones.SQLException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ec.gbc.house.enumeraciones.EstadoIncidencia.getEnum;
import static ec.gbc.house.enumeraciones.EstadoIncidencia.values;

@LocalBean
@Stateless
public class RegistroIncidencia {

@EJB
    IIncidenciaDao incidenciaDao;

    public Integer  guardarNuevaIncidencia(IncidenciaDTO incidenciaDTO) throws SQLException {
        Incidencia incidencia = new Incidencia();
        incidencia.setDescripcion(incidenciaDTO.getDescripcion());
        Empleado empleado = new Empleado();
        empleado.setId(incidenciaDTO.getIdEmpleado());
        incidencia.setEmpleado(empleado);
        incidencia.setEstado(EstadoRegistro.ACTIVO);
        incidencia.setPrioridad(incidenciaDTO.getSeveridad());
        incidencia.setFechaCreacion(incidenciaDTO.getFechaIngreso());
        incidencia.setFechaVencimiento(incidenciaDTO.getFechaVencimiento());
        incidencia.setDescripcion(incidenciaDTO.getDescripcion());
        incidencia.setEstadoIncidencia(EstadoIncidencia.ABIERTA);
        incidencia.setNombre(incidenciaDTO.getNombre());
        Incidencia guardar = this.incidenciaDao.guardar(incidencia);
        return guardar.getId();
    }

    public List<IncidenciaDTO> buscarIncidenciaPorEstado(Integer estado) throws SQLException {
        List<Incidencia> lista = this.incidenciaDao.buscarIncidenciasPorEstado(getEnum(estado));
        return getIncidenciaDTOS(lista);
    }

    private List<IncidenciaDTO> getIncidenciaDTOS(List<Incidencia> lista) {
        List<IncidenciaDTO> empleados = lista.stream().map(new Function<Incidencia, IncidenciaDTO>() {
            @Override
            public IncidenciaDTO apply(Incidencia entidad) {
                IncidenciaDTO resultado = new IncidenciaDTO(entidad.getId(), entidad.getNombre(),entidad.getDescripcion(), entidad.getFechaCreacion(), entidad.getFechaVencimiento(),entidad.getPrioridad(),entidad.getEstadoIncidencia().name());
                resultado.setIdEmpleado(entidad.getEmpleado().getId());
                resultado.setNombreUsuario(entidad.getEmpleado().getNombre().concat(" ").concat(entidad.getEmpleado().getApellido()));
                resultado.setComentario(entidad.getComentario());
                resultado.setFechaCierre(entidad.getFechaCierre());
                return resultado;
            }
        }).collect(Collectors.toList());

        return empleados;
    }

    public void actualizar(IncidenciaDTO entity) throws SQLException {
        Incidencia incidencia = this.incidenciaDao.buscarPorId(Incidencia.class,entity.getId());
        if(incidencia != null && incidencia.getFechaCierre() == null){
            if(entity.getFechaCierre() !=null) {
                incidencia.setFechaCierre(entity.getFechaCierre());
                incidencia.setEstadoIncidencia(EstadoIncidencia.CERRADA);
            }
            incidencia.setComentario(entity.getComentario());
            this.incidenciaDao.actualizar(incidencia);
        }
        else{
            throw new SQLException("No se puede cambiar esta incidencia, revise que no se encuentre cerrada");
        }
    }

    public List<IncidenciaDTO> obtenerTodasIncidencias() throws SQLException {
        List<Incidencia> lista = this.incidenciaDao.buscarIncidencias();
        return getIncidenciaDTOS(lista);
    }
}
