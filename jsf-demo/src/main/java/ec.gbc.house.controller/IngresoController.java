package ec.gbc.house.controller;


import ec.gbc.house.dto.EmpleadoDTO;
import ec.gbc.house.excepciones.SQLException;
import ec.gbc.house.servicios.AdministracionEmpleado;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("ingresoController")
@ViewScoped
public class IngresoController implements Serializable {

    private Integer id;
    private String nombre;
    private String apellido;
    private String username;
    private String message;

    private List<EmpleadoDTO> empleados;

    @EJB
    AdministracionEmpleado administracion;

    @PostConstruct
    public void init(){
        try {
            obtenerEmpleados();
        } catch (SQLException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "No se puede cargar los empleados") );
        }
    }

    public void guardar(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            administracion.guardarEmpleado(username, nombre, apellido);
            this.limpiar();
            obtenerEmpleados();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto",  "Se ha guardado el registro correctamente") );

        } catch (SQLException e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "No se puede guardar su registro") );
        }catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  e.getLocalizedMessage()) );
        }
    }

    private void obtenerEmpleados() throws SQLException {
        this.empleados = this.administracion.buscarEmpleados();
    }

    public void limpiar(){
        this.setId(null) ;
        this.setUsername (null);
        this.setNombre(null);
        this.setApellido(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDTO> empleados) {
        this.empleados = empleados;
    }
}
