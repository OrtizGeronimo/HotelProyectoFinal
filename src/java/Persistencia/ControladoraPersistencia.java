package Persistencia;

import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Reserva;
import Logica.TipoHabitacion;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    EmpleadoJpaController empJpa = new EmpleadoJpaController();
    HabitacionJpaController habitacionJpa = new HabitacionJpaController();
    HuespedJpaController huespedJpa = new HuespedJpaController();
    ReservaJpaController reservaJpa = new ReservaJpaController();
    TipoHabitacionJpaController tipoJpa = new TipoHabitacionJpaController();

    /*
        TRAER
     */
    public List<Empleado> traerEmpleados() {

        return empJpa.findEmpleadoEntities();

    }

    public Empleado traerEmpleado(int id) {
       
        return empJpa.findEmpleado(id);
    }

    public List<Habitacion> traerHabitaciones() {

        return habitacionJpa.findHabitacionEntities();
    }

    public List<Huesped> traerHuespedes() {

        return huespedJpa.findHuespedEntities();

    }

    public Huesped traerHuesped(int id) {
        return huespedJpa.findHuesped(id);
    }

    public List<TipoHabitacion> traerTipoHabitaciones() {

        return tipoJpa.findTipoHabitacionEntities();
    }

    public TipoHabitacion traerTipoHabitaciones(String nombre) {
        return tipoJpa.findTipoHabitacion(nombre);
    }
    
    public List<Reserva> traerReservas() {
        return reservaJpa.findReservaEntities();
    }

    /*
        ALTAS
     */
    public void altaHuesped(Huesped huesped) {
        try {
            huespedJpa.create(huesped);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void altaEmpleado(Empleado e) {
        empJpa.create(e);
    }

    public void altaHabitacion(Habitacion habitacion) {

        try {
            habitacionJpa.create(habitacion);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void altaReserva(Reserva reserva) {

        reservaJpa.create(reserva);
    }

    public void altaTipoHabitacion(TipoHabitacion tipo) {
        try {
            tipoJpa.create(tipo);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
        BORRAR
     */
    public void borrarHuesped(int id) {
        try {
            huespedJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarEmpleado(int id) {
        try {
            empJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void borrarReserva(int id) {
        try {
            reservaJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
        MODIFICAR
     */
    public void modificarHuesped(Huesped h) {

        try {
            huespedJpa.edit(h);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarEmpleado(Empleado e) {
        try {
            empJpa.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarHabitacion(Habitacion habitacion) {

        try {
            habitacionJpa.edit(habitacion);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    

}
