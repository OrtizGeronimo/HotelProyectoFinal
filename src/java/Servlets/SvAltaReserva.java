package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvAltaReserva", urlPatterns = {"/SvAltaReserva"})
public class SvAltaReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Controladora control = new Controladora();
            SimpleDateFormat formato =new SimpleDateFormat("dd/MM/yyyy");
        try {
            int idUsuario = (int) request.getSession().getAttribute("id_usuario");
            System.out.println("ID USUARIO---" + idUsuario + " ------");
            int idHuesped = Integer.parseInt(request.getParameter("id_huesped"));
            String fechaCheckInString = request.getParameter("fechaCheckIn");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String tipo = request.getParameter("tipo");
            Date fechaCheckIn = formato.parse(fechaCheckInString);
            String fechaCheckOutString = request.getParameter("fechaCheckOut");
            Date fechaCheckOut = formato.parse(fechaCheckOutString);
            Date fechaActual = new Date();
            
            formato.format(fechaActual);
            
            Reserva reserva = control.altaReserva(idUsuario,idHuesped,fechaCheckIn,fechaCheckOut,cantidad,tipo,fechaActual);
           
            if (reserva != null) {
                 long montoTotal = control.calcularMonto(reserva);
                response.sendRedirect("registro_exitoso.jsp");
                request.getSession().setAttribute("habitacion", reserva.getHabitacion().getNum());
                request.getSession().setAttribute("piso", reserva.getHabitacion().getPiso());
                request.getSession().setAttribute("monto", montoTotal);
                
            } else {
                response.sendRedirect("error_reserva.jsp");
                request.getSession().setAttribute("tipo", tipo);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(SvAltaReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
