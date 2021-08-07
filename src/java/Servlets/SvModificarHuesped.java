package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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

@WebServlet(name = "SvModificarHuesped", urlPatterns = {"/SvModificarHuesped"})
public class SvModificarHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("first_name");
            String apellido = request.getParameter("last_name");
            String profesion = request.getParameter("profesion");
            String direccion = request.getParameter("direccion");
            int dni = Integer.parseInt(request.getParameter("dni"));
            String fechaNacString = request.getParameter("fechaNac");
            Date fechaNac = fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacString);
            
            Controladora control = new Controladora();
            Huesped h = control.traerHuesped(id);
            
            h.setApellido(apellido);
            h.setDireccion(direccion);
            h.setDni(dni);
            h.setFechaNac(fechaNac);
            h.setNombre(nombre);
            h.setProfesion(profesion);
            
            control.modificarHuesped(h);
            
            //una vez hacemos todo, se actualiza la lista EN LA SESION
            request.getSession().setAttribute("listaHuesped", control.traerHuespedes());
            response.sendRedirect("ver_huespedes.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarHuesped.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Controladora control = new Controladora();
        int id = Integer.parseInt(request.getParameter("id"));
        Huesped h = control.traerHuesped(id);

        request.getSession().setAttribute("huesped", h);
        response.sendRedirect("modificar_huesped.jsp");
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
