<%@page import="Logica.Huesped"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Colorlib Templates">
        <meta name="author" content="Colorlib">
        <meta name="keywords" content="Colorlib Templates">

        <!-- Title Page-->
        <title>Modificar Huesped</title>

        <!-- Icons font CSS-->
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="assets/css/main.css" rel="stylesheet" media="all">
    </head>
    <%
           HttpSession mySession = request.getSession();
           Huesped h = (Huesped) mySession.getAttribute("huesped");
           SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
           String fecha = dateFormat.format(h.getFechaNac());
    %>
    <body>
        <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
            <div class="wrapper wrapper--w790">
                <div class="card card-5">
                    <div class="card-heading">
                        <h2 class="title">Modificar Huesped</h2>
                    </div>
                    <div class="card-body">
                        <form action="SvModificarHuesped" method="GET">
                            <div class="form-row m-b-55">
                                <div class="name">Nombre</div>
                                <div class="value">
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <div class="input-group-desc">
                                                <input class="input--style-5" type="text" name="first_name" value="<%= h.getNombre() %>">
                                                <label class="label--desc">Nombre</label>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <div class="input-group-desc">
                                                <input class="input--style-5" type="text" name="last_name" value="<%= h.getApellido() %>">
                                                <label class="label--desc">Apellido</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Direcci?n</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="direccion" value="<%= h.getDireccion() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Profesi?n</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="profesion" value="<%= h.getProfesion() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row m-b-55">
                                <div class="name">DNI</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="dni" value="<%= h.getDni() %>">
                                    </div>
                                </div>

                            </div>
                            <div class="form-row">
                                <div class="name">Fecha de Nacimiento</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="fechaNac" value="<%= fecha %>">
                                    </div>
                                </div>
                            </div>

                            <div>
                                <input type="hidden" name="id" value="<%= h.getId() %>">
                                <button class="btn btn--radius-2 btn--red" type="submit">Modificar</button>
                            </div>
                                <div class="flecha">
                            <a href="ver_huespedes.jsp"><i class="fa fa-arrow-left fa-2x" aria-hidden="true"></i></a>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Jquery JS-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Vendor JS-->
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/datepicker/moment.min.js"></script>
        <script src="vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="js/global.js"></script>

    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
