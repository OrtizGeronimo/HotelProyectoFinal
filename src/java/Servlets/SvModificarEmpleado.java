package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Huesped;
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

@WebServlet(name = "SvModificarEmpleado", urlPatterns = {"/SvModificarEmpleado"})
public class SvModificarEmpleado extends HttpServlet {

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
            String cargo = request.getParameter("cargo");
            String direccion = request.getParameter("direccion");
            String user = request.getParameter("usuario");
            String psw = request.getParameter("psw");
            int dni = Integer.parseInt(request.getParameter("dni"));
            String fechaNacString = request.getParameter("fechaNac");
            Date fechaNac = fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacString);
            
            Controladora control = new Controladora();
            Empleado e = control.traerEmpleado(id);
            
            e.setApellido(apellido);
            e.setDireccion(direccion);
            e.setDni(dni);
            e.setFechaNac(fechaNac);
            e.setNombre(nombre);
            e.setCargo(cargo);
            e.setUsuario(user);
            e.setContraseña(psw);
            
            control.modificarEmpleado(e);
            
            //una vez hacemos todo, se actualiza la lista EN LA SESION
            request.getSession().setAttribute("listaEmpleados", control.traerEmpleados());
            response.sendRedirect("ver_empleados.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarHuesped.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controladora control = new Controladora();
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado e = control.traerEmpleado(id);

        request.getSession().setAttribute("empleado", e);
        response.sendRedirect("modificar_empleado.jsp");
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
