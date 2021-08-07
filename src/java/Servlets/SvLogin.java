
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

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
        
       HttpSession session = request.getSession();
        
        String usuario = request.getParameter("user");
        String contraseña = request.getParameter("password");
        Controladora control = new Controladora();
        Empleado verifico = control.comprobarUsuario(usuario, contraseña);
        
        if (verifico != null) {
            session.setAttribute("id_usuario", verifico.getId());
            session.setAttribute("usuario", usuario);
            control.altaAutomaticaHabitacion();
            response.sendRedirect("inicio.jsp");
            
        } else {
            response.sendRedirect("index.jsp");
        }
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
