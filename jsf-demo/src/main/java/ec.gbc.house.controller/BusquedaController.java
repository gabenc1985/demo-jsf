package ec.gbc.house.controller;


import ec.gbc.house.dto.IncidenciaDTO;
import ec.gbc.house.excepciones.SQLException;
import ec.gbc.house.servicios.RegistroIncidencia;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("busquedaController")
@ViewScoped
public class BusquedaController implements Serializable {



private Integer estado;
    private List<IncidenciaDTO> incidencias;

    @EJB
    RegistroIncidencia registroIncidencia;

    @PostConstruct
    public void init(){
        try {
            cargarIncidencias();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "No se puede cargar los empleados") );
        }
    }
    public void buscarIncidencias(SelectEvent event){
        Integer selectedItem = (Integer) event.getObject();

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if(selectedItem > 0) {
                incidencias = registroIncidencia.buscarIncidenciaPorEstado(selectedItem);
            }else{
                incidencias = registroIncidencia.obtenerTodasIncidencias();
            }
            if(incidencias==null || incidencias.isEmpty()){
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información",  "No se han encontrado los registros") );
            }
        } catch (SQLException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "No se puede cargar los registros") );
        }
    }
     public void cargarIncidencias(){
         FacesContext context = FacesContext.getCurrentInstance();
         try {
             incidencias = registroIncidencia.obtenerTodasIncidencias();

             if(incidencias==null || incidencias.isEmpty()){
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información",  "No se han encontrado los registros") );
             }
         } catch (SQLException e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "No se puede cargar los registros") );
         }
     }

     public void actualizarRegistro(RowEditEvent event) {
             IncidenciaDTO entity =  (IncidenciaDTO)event.getObject();

            System.out.println(entity.getComentario());
         try {
             this.registroIncidencia.actualizar(entity);
         } catch ( Exception e) {
             FacesContext context = FacesContext.getCurrentInstance();
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  e.getLocalizedMessage()) );
         }
     }

    public List<IncidenciaDTO> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<IncidenciaDTO> incidencias) {
        this.incidencias = incidencias;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
