package ec.gbc.house.dto;

import ec.gbc.house.enumeraciones.EstadoIncidencia;
import ec.gbc.house.enumeraciones.PrioridadIncidencia;

import java.util.Date;

public class IncidenciaDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fechaIngreso;
    private Date fechaVencimiento;
    private Integer severidad;
    private String nombreSeveridad;
    private String estado;
    private String nombreUsuario;
    private String username;
    private Integer idEmpleado;
    private String comentario;
    private Date fechaCierre;


    public IncidenciaDTO(){
    }

    public IncidenciaDTO(Integer id, String nombre, String descripcion, Date fechaIngreso, Date fechaVencimiento, Integer severidad, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.severidad = severidad;
        this.estado = estado;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getNombreSeveridad() {
        nombreSeveridad = PrioridadIncidencia.getEnum(severidad).name();
        return nombreSeveridad;
    }

    public void setNombreSeveridad(String nombreSeveridad) {
        this.nombreSeveridad = nombreSeveridad;
    }
}
