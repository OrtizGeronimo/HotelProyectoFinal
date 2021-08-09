
package Servlets;

import Logica.Controladora;
import java.io.IOException;
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


@WebServlet(name = "SvAltaEmpleado", urlPatterns = {"/SvAltaEmpleado"})
public class SvAltaEmpleado extends HttpServlet {

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
        try {
            String nombre = request.getParameter("first_name");
            String apellido = request.getParameter("last_name");
            String cargo = request.getParameter("cargo");
            String usuario = request.getParameter("usuario");
            String psw = request.getParameter("psw");
            String direccion = request.getParameter("direccion");
            int dni = Integer.parseInt(request.getParameter("dni"));
            String fechaNacString = request.getParameter("fechaNac");
            Date fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacString);
            
            Controladora control = new Controladora();
            control.altaEmpleado(nombre, apellido, dni, cargo, direccion, fechaNac,usuario,psw);
            
            response.sendRedirect("alta_exitosa.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvAltaHuesped.class.getName()).log(Level.SEVERE, null, ex);
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
