<%@page import="Logica.Huesped"%>
<%@page import="java.util.List"%>

<!doctype html>
<html lang="es">
    <head>
        <title>Huespedes</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="assets/css/style.css">
        
    </head>
    <body>
        <%
         
        HttpSession mySession = request.getSession();
        String usuario = (String) mySession.getAttribute("usuario");
        if (usuario == null) {    
            response.sendRedirect("index.jsp");
        } else {
            List<Huesped> listaHuesped = (List) request.getSession().getAttribute("listaHuesped");
            
        %>
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">HUESPEDES</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="table-wrap">
                            <table class="table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>ID no.</th>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Profesión</th>
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% 
                                       for(Huesped h: listaHuesped){
                                    
                                    %>
                                    <tr class="alert" role="alert">
                                        <th scope="row"><%= h.getDni() %></th>
                                        <td><%= h.getNombre() %></td>
                                        <td><%= h.getApellido() %></td>
                                        <td><%= h.getProfesion() %></td>
                                        <td>
                                            <form action="SvEliminar" method="POST">
                                                    <input type="hidden" name="id" value="<%= h.getId() %>" >
                                                    <button type="submit"> <span aria-hidden="true"><i class="fa fa-close close"></i></span> </button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="SvModificarHuesped" method="POST">
                                                    <input type="hidden" name="id" value="<%= h.getId() %>" >
                                                    <button type="submit"> <span aria-hidden="true"><i class="fa fa-pencil-square-o"></i></span> </button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%
                                       }
                                        
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                                <a href="inicio.jsp" class="boton_volver">Volver</a>
            </div>
        </section>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <%
          }
                                        
        %>
    </body>
</html>
