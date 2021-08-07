package Logica;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@CascadeOnDelete
public class Empleado extends Persona {

    @Basic
    private String cargo;
    private String usuario;
    private String contraseña;
    @OneToMany
    @CascadeOnDelete
    private List<Reserva> listaReservas;

    public Empleado() {
        super();
    }

    public Empleado(String cargo, String usuario, String contraseña, List<Reserva> listaReservas, int id, int dni, String nombre, String apellido, Date fechaNac, String direccion) {
        super(id, dni, nombre, apellido, fechaNac, direccion);
        this.cargo = cargo;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.listaReservas = listaReservas;
    }

    
    
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

}
