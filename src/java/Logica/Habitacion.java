
package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@CascadeOnDelete
public class Habitacion implements Serializable {
    @Id
    private int num;
    @Basic
    private int piso;
    private String nombre;
    private int precioXNoche;
    @OneToMany
    @CascadeOnDelete
    private List<Reserva> listaReservas;
    @ManyToOne
    private TipoHabitacion tipo;

    public Habitacion() {
    }

    public Habitacion(int num, int piso, String nombre, int precioXNoche, TipoHabitacion tipo) {
        this.num = num;
        this.piso = piso;
        this.nombre = nombre;
        this.precioXNoche = precioXNoche;
        this.tipo = tipo;
    }

    

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioXNoche() {
        return precioXNoche;
    }

    public void setPrecioXNoche(int precioXNoche) {
        this.precioXNoche = precioXNoche;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }
    
    
    
}
