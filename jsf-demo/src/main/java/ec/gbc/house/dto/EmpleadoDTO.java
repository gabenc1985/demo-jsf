package ec.gbc.house.dto;

public class EmpleadoDTO {

    private Integer id;
    private String username;
    private String nombre;
    private String apellido;
    private String nombreCompleto;

    public EmpleadoDTO(){

    }

    public EmpleadoDTO(Integer id, String username, String nombre, String apellido) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getNombreCompleto() {
        if(this.nombre!=null)
            this.nombreCompleto = this.nombre.concat(" ").concat(this.apellido);
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
