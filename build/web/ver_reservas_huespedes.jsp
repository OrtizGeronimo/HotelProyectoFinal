<%@page import="Logica.Reserva"%>
<%@page import="Logica.Huesped"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<!doctype html>
<html lang="es">
    <head>
        <title>Reservas</title>
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
            List<Reserva> listaReservas = (List) request.getSession().getAttribute("listaReservas");
        %>
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">RESERVAS</h2>
                    </div>
                </div>
                <form action="SvReservasHuesped" method="POST">
                    <div class="form-row">
                        <div class="name">Huesped</div>
                        <div class="value">
                            <div class="input-group">
                                <select name="id_huesped" class="select_ver">
                                    <option></option>
                                    <%
                                        for(Huesped h: listaHuesped){
                                    %>

                                    <option value="<%= h.getId() %>" > <%= h.getNombre() + " " + h.getApellido()%></option>
                                    <%    
                                        }
                                    %>
                                </select>
                                <div class="name">Per?odo Desde</div>
                                <input class="input--style-5 input_ver" type="text" name="fechaDesde" value="<%= mySession.getAttribute("fechaDesde")%>">
                                <div class="name">Hasta</div>
                                <input class="input--style-5" type="text" name="fechaHasta"value="<%= mySession.getAttribute("fechaHasta")%>">
                            </div>
                        </div>
                        <button type="submit" class="buscar_fecha">Buscar</button>
                        <div class="input-group">

                        </div>
                </form>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="table-wrap">
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th>ID no.</th>
                                    <th>Fecha CheckIn</th>
                                    <th>Fecha CheckOut</th>
                                    <th>Cantidad de Personas</th>
                                    <th>Habitaci?n</th>
                                    <th>Fecha de Reserva</th>


                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                   for(Reserva e: listaReservas){
                                       //if (e.getFechaCreacionReserva().equals(mySession.getAttribute()) )
                                %>
                                <tr class="alert" role="alert">
                                    <th scope="row"><%= e.getIdReserva() %></th>
                                    <td><%= new SimpleDateFormat("dd/MM/yyyy").format(e.getFechaCheckIn()) %></td>
                                    <td><%= new SimpleDateFormat("dd/MM/yyyy").format(e.getFechaCheckOut()) %></td>
                                    <td><%= e.getCantPersonas() %></td>
                                    <td><%= e.getHabitacion().getNum() %></td>
                                    <td><%= new SimpleDateFormat("dd/MM/yyyy").format(e.getFechaCreacionReserva()) %></td>

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
