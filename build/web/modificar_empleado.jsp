<%@page import="Logica.Empleado"%>
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
        <title>Modificar Empleado</title>

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
           Empleado e = (Empleado) mySession.getAttribute("empleado");
           SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
           String fecha = dateFormat.format(e.getFechaNac());
    %>
    <body>
        <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
            <div class="wrapper wrapper--w790">
                <div class="card card-5">
                    <div class="card-heading">
                        <h2 class="title">Modificar Empleado</h2>
                    </div>
                    <div class="card-body">
                        <form action="SvModificarEmpleado" method="GET">
                            <div class="form-row m-b-55">
                                <div class="name">Nombre</div>
                                <div class="value">
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <div class="input-group-desc">
                                                <input class="input--style-5" type="text" name="first_name" value="<%= e.getNombre() %>">
                                                <label class="label--desc">Nombre</label>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <div class="input-group-desc">
                                                <input class="input--style-5" type="text" name="last_name" value="<%= e.getApellido() %>">
                                                <label class="label--desc">Apellido</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Direcci�n</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="direccion" value="<%= e.getDireccion() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row m-b-55">
                                <div class="name">DNI</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="dni" value="<%= e.getDni() %>">
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
                            <div class="form-row">
                                <div class="name">Cargo</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="cargo" value="<%= e.getCargo() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Usuario</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="usuario" value="<%= e.getUsuario() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="name">Contrase�a</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="psw" value="<%= e.getContrase�a() %>">
                                    </div>
                                </div>
                            </div>

                            <div>
                                <input type="hidden" name="id" value="<%= e.getId() %>">
                                <button class="btn btn--radius-2 btn--red" type="submit">Modificar</button>
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
