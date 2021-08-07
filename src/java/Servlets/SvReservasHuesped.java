package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import Logica.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvReservasHuesped", urlPatterns = {"/SvReservasHuesped"})
public class SvReservasHuesped extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id_huesped"));
        Huesped h = control.traerHuesped(id);
        List<Reserva> listaReservas = h.getListaReservas();
        request.getSession().setAttribute("listaReservas", listaReservas);
        response.sendRedirect("ver_reservas_huespedes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
