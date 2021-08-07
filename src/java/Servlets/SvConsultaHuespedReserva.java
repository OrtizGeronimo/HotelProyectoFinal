
package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import Logica.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvConsultaHuespedReserva", urlPatterns = {"/SvConsultaHuespedReserva"})
public class SvConsultaHuespedReserva extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Controladora control = new Controladora();
        List<Huesped> listaHuesped = control.traerHuespedes();
        HttpSession mySession = request.getSession();
        List<Reserva> listaReservas = new ArrayList<>();
        request.getSession().setAttribute("listaReservas", listaReservas);
        mySession.setAttribute("listaHuesped", listaHuesped);
        response.sendRedirect("ver_reservas_huespedes.jsp");

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
