package Logica;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@CascadeOnDelete
public class Huesped extends Persona {

    @Basic
    private String profesion;
    @OneToMany
    @CascadeOnDelete
    private List<Reserva> listaReservas;

    public Huesped() {
        super();
    }

    public Huesped(String profesion, List<Reserva> listaReservas, int id, int dni, String nombre, String apellido, Date fechaNac, String direccion) {
        super(id, dni, nombre, apellido, fechaNac, direccion);
        this.profesion = profesion;
        this.listaReservas = listaReservas;
    }

    

    

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

}
