
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
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


@WebServlet(name = "SvReservasEmpleado", urlPatterns = {"/SvReservasEmpleado"})
public class SvReservasEmpleado extends HttpServlet {

    
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
        int id = Integer.parseInt(request.getParameter("id_empleado"));
        Empleado h = control.traerEmpleado(id);
        List<Reserva> listaReservas = h.getListaReservas();
        request.getSession().setAttribute("listaReservas", listaReservas);
        response.sendRedirect("ver_reservas_empleados.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
