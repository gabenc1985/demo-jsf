package ec.gbc.house.controller;

import ec.gbc.house.dto.EmpleadoDTO;
import ec.gbc.house.dto.IncidenciaDTO;
import ec.gbc.house.enumeraciones.EstadoRegistro;
import ec.gbc.house.excepciones.SQLException;
import ec.gbc.house.servicios.AdministracionEmpleado;
import ec.gbc.house.servicios.RegistroIncidencia;
import org.primefaces.event.SelectEvent;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("incidenciaController")
@ViewScoped
public class IncidenciaController implements Serializable {

    private Date fechaIngreso;

    private Date fechaVencimiento;

    private Integer severidad;

    private Integer idEmpleado;

    private String titulo;

    private String descripcion;



    private List<EmpleadoDTO> empleados;

    @EJB
    private AdministracionEmpleado administracionEmpleado;

    @EJB
    private RegistroIncidencia registroIncidencia;

    public List<EmpleadoDTO> buscarEmpleados(String username) {
        List<EmpleadoDTO>  empleados =  administracionEmpleado.buscarEmpleadosPorUsername(username);
        if(empleados == null || empleados.isEmpty()){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información",  "No existen usuarios con es username.") );
        }
        return empleados;
    }

    public void guardar(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            IncidenciaDTO incidencia = new IncidenciaDTO();
            incidencia.setDescripcion(getDescripcion());
            incidencia.setNombre(getTitulo());
            incidencia.setFechaIngreso(getFechaIngreso());
            incidencia.setFechaVencimiento(getFechaVencimiento());
            incidencia.setSeveridad(getSeveridad());
            incidencia.setIdEmpleado(getIdEmpleado());
            Integer idIncidencia = registroIncidencia.guardarNuevaIncidencia(incidencia);
            this.limpiar();

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto",  "Se ha guardado el registro correctamente con id: " + idIncidencia ) );

        } catch (SQLException e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "No se puede guardar su registro") );
        }catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  e.getLocalizedMessage()) );
        }

    }

    public void limpiar(){
        this.setDescripcion(null);
        this.setFechaIngreso(new Date());
        this.setFechaVencimiento(null);
        this.setSeveridad(null);
        this.setTitulo(null);
        this.setIdEmpleado(null);
    }
    public void cargarInformacion(SelectEvent event) {
        Object item = event.getObject();
        System.out.println(item.toString());
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getSeveridad() {
        return severidad;
    }

    public void setSeveridad(Integer severidad) {
        this.severidad = severidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }


}
