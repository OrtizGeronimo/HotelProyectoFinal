package Logica;

import Persistencia.ControladoraPersistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controladora {

    ControladoraPersistencia control = new ControladoraPersistencia();

    public Empleado comprobarUsuario(String usuario, String contraseña) {

        List<Empleado> listaEmpleados = control.traerEmpleados();

        if (listaEmpleados != null) {
            /**
             * recorremos la lista, si encontramos coincidencia termina la
             * función, sino, sigue buscando, si nunca encontró coincidencia
             * entonces va a llegar al return false
             *
             */
            for (Empleado empleado : listaEmpleados) {
                if (empleado.getUsuario().equals(usuario) && empleado.getContraseña().equals(contraseña)) {

                    return empleado;
                }
            }
        }
        return null;

    }

    /*
        ALTAS
     */
    public void altaHuesped(String nombre, String apellido, int dni, String profesion, String direccion, Date fechaNac) {

        Huesped huesped = new Huesped();

        huesped.setNombre(nombre);
        huesped.setApellido(apellido);
        huesped.setDni(dni);
        huesped.setFechaNac(fechaNac);
        huesped.setProfesion(profesion);
        huesped.setDireccion(direccion);

        control.altaHuesped(huesped);
    }

    public void altaEmpleado(String nombre, String apellido, int dni, String cargo, String direccion, Date fechaNac, String usuario, String psw) {

        Empleado e = new Empleado();
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setDni(dni);
        e.setFechaNac(fechaNac);
        e.setCargo(cargo);
        e.setDireccion(direccion);
        e.setUsuario(usuario);
        e.setContraseña(psw);
        control.altaEmpleado(e);
    }

    public boolean altaReserva(int idUsuario, int idHuesped, Date fechaCheckIn, Date fechaCheckOut, int cantidad, String tipo, Date fechaCreacion) {

        boolean hacerReserva = true;
        Habitacion habitacion = null;
        fechaCreacion.setHours(0);
        fechaCreacion.setMinutes(0);
        fechaCreacion.setSeconds(0);
        for (Habitacion habitaciones : control.traerHabitaciones()) {
            hacerReserva = true;
            if (habitaciones.getTipo().getNombreTipoHabitacion().equalsIgnoreCase(tipo)) {
                for (Reserva reserva : habitaciones.getListaReservas()) {
                    if (!(fechaCheckIn.before(reserva.getFechaCheckIn()) && (fechaCheckOut.before(reserva.getFechaCheckIn()) || fechaCheckOut.equals(reserva.getFechaCheckIn()))) && !((fechaCheckIn.after(reserva.getFechaCheckOut()) || fechaCheckIn.equals(reserva.getFechaCheckOut())) && fechaCheckOut.after(reserva.getFechaCheckOut()))) {
                        hacerReserva = false;
                        break;
                    }
                    
                }
            } else {
                hacerReserva = false;
            }
            if (hacerReserva) {
                habitacion = habitaciones;
                break;
            }
        }

        if (hacerReserva) {
            Reserva reserva = new Reserva();
            reserva.setCantPersonas(cantidad);
            reserva.setFechaCheckIn(fechaCheckIn);
            reserva.setFechaCheckOut(fechaCheckOut);
            reserva.setFechaCreacionReserva(fechaCreacion); //fecha de hoy
            reserva.setHabitacion(habitacion);

            Empleado e = control.traerEmpleado(idUsuario);
            Huesped h = control.traerHuesped(idHuesped);

            e.getListaReservas().add(reserva);
            h.getListaReservas().add(reserva);
            habitacion.getListaReservas().add(reserva);

            control.altaReserva(reserva);
            control.modificarEmpleado(e);
            control.modificarHuesped(h);
            control.modificarHabitacion(habitacion);

            return true;
        } else {
            return false;
        }

    }

    public void altaAutomaticaHabitacion() {
        TipoHabitacion single;
        TipoHabitacion doble;
        TipoHabitacion triple;
        TipoHabitacion multiple;
        if (control.traerTipoHabitaciones().isEmpty()) {
            single = new TipoHabitacion("single");
            doble = new TipoHabitacion("doble");
            triple = new TipoHabitacion("triple");
            multiple = new TipoHabitacion("multiple");
            control.altaTipoHabitacion(single);
            control.altaTipoHabitacion(doble);
            control.altaTipoHabitacion(triple);
            control.altaTipoHabitacion(multiple);
        } else {
            single = control.traerTipoHabitaciones("single");
            doble = control.traerTipoHabitaciones("doble");
            triple = control.traerTipoHabitaciones("triple");
            multiple = control.traerTipoHabitaciones("multiple");
        }

        List<Habitacion> listaHabitaciones = control.traerHabitaciones();

        if (listaHabitaciones.isEmpty()) {
            //DAMOS DE ALTA AUTOMATICAMENTE LAS HABITACIONES, HACEMOS LAS 10 DEL PRIMER PISO Y LAS 10 DEL SEGUNDO
            for (int i = 1; i < 11; i++) {
                Habitacion habitacion = new Habitacion();
                habitacion.setNum(i);
                habitacion.setPiso(1);
                if (i < 6) {
                    habitacion.setTipo(single);
                    habitacion.setPrecioXNoche(1000);
                } else {
                    habitacion.setTipo(doble);
                    habitacion.setPrecioXNoche(1600);
                }
                habitacion.setNombre("-");
                control.altaHabitacion(habitacion);
            }

            for (int i = 11; i < 21; i++) {
                Habitacion habitacion = new Habitacion();
                habitacion.setNum(i);
                habitacion.setPiso(2);
                if (i < 16) {
                    habitacion.setTipo(triple);
                    habitacion.setPrecioXNoche(2200);
                } else {
                    habitacion.setTipo(multiple);
                    habitacion.setPrecioXNoche(3000);
                }
                habitacion.setNombre("-");
                control.altaHabitacion(habitacion);
            }
        }

    }

    /*
            TRAER
     */
    public List<Huesped> traerHuespedes() {

        return control.traerHuespedes();
    }

    public Huesped traerHuesped(int id) {
        return control.traerHuesped(id);
    }

    public List<Empleado> traerEmpleados() {
        return control.traerEmpleados();
    }

    public Empleado traerEmpleado(int id) {
        return control.traerEmpleado(id);
    }

    public List<Reserva> traerReservasCondicionadas(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        List<Reserva> listaCondicionada = new ArrayList<>();

        for (Reserva r : control.traerReservas()) {

            if (formato.format(r.getFechaCreacionReserva()).equals(formato.format(fecha))) {
                listaCondicionada.add(r);
            }
        }
        return listaCondicionada;
    }

    public List<Reserva> traerReservas() {
        return control.traerReservas();
    }
    public List<Reserva> traerReservasPeriodo(Huesped h, String fechaDesde, String fechaHasta) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date desde = new Date();
        Date hasta = new Date();
        try {
             desde = formato.parse(fechaDesde);
             hasta = formato.parse(fechaHasta);
        } catch (ParseException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Reserva> listaReservaPeriodo = new ArrayList<>();
        for (Reserva reservas : h.getListaReservas()) {
            if ((reservas.getFechaCreacionReserva().after(desde) || formato.format(reservas.getFechaCreacionReserva()).equals(formato.format(desde))) && (reservas.getFechaCreacionReserva().before(hasta) || formato.format(reservas.getFechaCreacionReserva()).equals(formato.format(hasta)))) {
                listaReservaPeriodo.add(reservas);
            } 
        }
        return listaReservaPeriodo;
    }

    /*
        BORRAR
     */
    public void borrarHuesped(int id) {
        control.borrarHuesped(id);
    }

    public void borrarEmpleado(int id) {
        control.borrarEmpleado(id);
    }

    public void borrarReserva(int id) {
        control.borrarReserva(id);
    }

    /*
        MODIFICAR
     */
    public void modificarHuesped(Huesped h) {
        control.modificarHuesped(h);
    }

    public void modificarEmpleado(Empleado e) {
        control.modificarEmpleado(e);
    }

    

}
