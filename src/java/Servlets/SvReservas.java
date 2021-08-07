
package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvReservas", urlPatterns = {"/SvReservas"})
public class SvReservas extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Reserva> listaReservas = new ArrayList<>();
        request.getSession().setAttribute("listaReservas", listaReservas);
        request.getSession().setAttribute("fecha", "");
        response.sendRedirect("ver_reservas_dia.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Controladora control = new Controladora();
            String fechaForm = request.getParameter("fechaABuscar");
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaForm);
            List<Reserva> listaReservas = control.traerReservasCondicionadas(fecha);
            
            request.getSession().setAttribute("fecha", fechaForm);
            request.getSession().setAttribute("listaReservas", listaReservas);
            response.sendRedirect("ver_reservas_dia.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvReservas.class.getName()).log(Level.SEVERE, null, ex);
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
