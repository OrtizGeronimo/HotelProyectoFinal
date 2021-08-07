package Logica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva implements Serializable {

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReserva;
    @Basic
    private int cantPersonas;
    @ManyToOne
    private Habitacion habitacion;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionReserva;
    @Temporal(TemporalType.DATE)
    private Date fechaCheckIn;
    @Temporal(TemporalType.DATE)
    private Date fechaCheckOut;
    

    
    
    public Reserva() {
    }

    public Reserva(long idReserva, int cantPersonas, Date fechaCheckIn, Date fechaCheckOut) {
        
        this.idReserva = idReserva;
        this.cantPersonas = cantPersonas;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
    }

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    

    public Date getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(Date fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public Date getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(Date fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Date getFechaCreacionReserva() {
        return fechaCreacionReserva;
    }

    public void setFechaCreacionReserva(Date fechaCreacionReserva) {
        this.fechaCreacionReserva = fechaCreacionReserva;
    }
    

}
