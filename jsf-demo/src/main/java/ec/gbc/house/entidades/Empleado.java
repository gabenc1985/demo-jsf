package ec.gbc.house.entidades;

import ec.gbc.house.converters.Estado;
import ec.gbc.house.enumeraciones.EstadoRegistro;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String username;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @OneToMany(mappedBy="empleado")
    private List<Incidencia> incidencias;

    @Convert(converter = Estado.class)
    private EstadoRegistro estado;

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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public EstadoRegistro getEstado() {
        return estado;
    }

    public void setEstado(EstadoRegistro estado) {
        this.estado = estado;
    }
}
