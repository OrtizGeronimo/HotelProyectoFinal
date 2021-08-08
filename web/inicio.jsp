
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Template</title>
        <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/inicio.css">
    </head>
    <body>
        <%
            
            HttpSession mySession = request.getSession();
            String usuario = (String) mySession.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("index.jsp");
            } else {
                usuario = usuario.toUpperCase();
        %>
        <div class="contenedor">
            <div class="cerrar-sesion">
                <form action="SvCerrarSesion" method="GET">
                    <a href="SvCerrarSesion">Cerrar Sesión</a>
                </form>
            </div>    
            <div class="centro">

                <h1 class="bienvenida">BIENVENIDO/A, <%= usuario %>!</h1>

                <div class="container-lista">
                    <ul class="lista">
                        <li>Reservas
                            <ul class="interior">
                                <form action="SvConsultaReserva" method="GET">
                                    <li><a href="SvConsultaReserva">Realizar Reserva</a></li>
                                </form>
                                <form action="SvReservas" method="GET">
                                    <li><a href="SvReservas">Ver Reservas</a></li>
                                </form>
                            </ul>
                        </li>
                        <li>Huespedes
                            <ul class="interior">
                                <li><a href="registro_huesped.jsp">Registrar Huesped</a></li>

                                <form action="SvConsultaHuesped" method="GET">
                                    <li><a href="SvConsultaHuesped">Ver Huespedes</a></li>
                                </form>
                                <form action="SvConsultaHuespedReserva" method="GET">
                                    <li><a href="SvConsultaHuespedReserva">Ver Reservas de Huesped</a></li>
                                </form>
                            </ul>
                        </li>
                        <li>Empleados
                            <ul class="interior">
                                <li><a href="registro_empleado.jsp">Registrar Empleado</a></li>
                                <form action="SvConsultaEmpleado" method="GET">
                                    <li><a href="SvConsultaEmpleado">Ver Empleados</a></li>
                                </form>

                                <form action="SvConsultaEmpleadoReserva" method="GET">
                                    <li><a href="SvConsultaEmpleadoReserva">Ver Reservas de Empleado</a></li>
                                </form>
                            </ul>
                        </li>

                    </ul>
                </div>

            </div>
        </div>
        <%}%>
    </body>
</html>
