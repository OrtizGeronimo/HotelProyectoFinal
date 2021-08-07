package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoHabitacion implements Serializable {

    @Id
    private String nombreTipoHabitacion;
    @OneToMany
    List<Habitacion> listaHabitaciones;

    public TipoHabitacion() {
    }

    public TipoHabitacion(String nombreTipoHabitacion) {
        this.nombreTipoHabitacion = nombreTipoHabitacion;
    }

    
    
    public TipoHabitacion(String nombreTipoHabitacion, List<Habitacion> listaHabitaciones) {
        this.nombreTipoHabitacion = nombreTipoHabitacion;
        this.listaHabitaciones = listaHabitaciones;
    }

    public String getNombreTipoHabitacion() {
        return nombreTipoHabitacion;
    }

    public void setNombreTipoHabitacion(String nombreTipoHabitacion) {
        this.nombreTipoHabitacion = nombreTipoHabitacion;
    }

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

}
