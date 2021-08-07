<%@page import="Logica.Huesped"%>
<%@page import="java.util.List"%>
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
        <title>Reserva</title>

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

    <body>

        <%
            HttpSession mySession = request.getSession();
            String usuario = (String) mySession.getAttribute("usuario");
            if (usuario == null) {    
                response.sendRedirect("index.jsp");
            } else {
                List<Huesped> listaHuesped = (List) request.getSession().getAttribute("listaHuesped");
        %>


        <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
            <div class="wrapper wrapper--w790">
                <div class="card card-5">
                    <div class="card-heading">
                        <h2 class="title">Realizar Reserva</h2>
                    </div>
                    <div class="card-body">
                        <form action="SvAltaReserva" method="POST">

                            <div class="form-row">
                                <div class="name">Huesped</div>
                                <div class="value">
                                    <div class="input-group">
                                        <select name="id_huesped">
                                            <%
                                                for(Huesped h: listaHuesped){
                                            %>
                                            
                                            <option value="<%= h.getId() %>" > <%= h.getNombre() + " " + h.getApellido()%></option>
                                            <%    
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-row m-b-55">
                            <div class="name">Habitaci�n</div>
                            <div class="value">
                                <div class="row row-space">
                                    <div class="col-2">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="number" name="cantidad" id="cantidad" min="1" max="6" onkeydown="return false" autocomplete="off">
                                            <label class="label--desc">Cantidad de Personas</label>
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <div class="input-group-desc">
                                            <select name="tipo" id="tipo">
                                                
                                            </select>
                                            <label class="label--desc">Tipo de Habitaci�n</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                            <div class="form-row">
                                <div class="name">Fecha Check-In</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="fechaCheckIn">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row m-b-55">
                                <div class="name">Fecha Check-Out</div>
                                <div class="value">
                                    <div class="input-group">
                                        <input class="input--style-5" type="text" name="fechaCheckOut">
                                    </div>
                                </div>

                            </div>
                            

                            <div>
                                <button class="btn btn--radius-2 btn--red" type="submit">Reservar</button>
                            </div>
                            <div>
                                <a href="inicio.jsp"><i class="fa fa-arrow-left fa-lg" aria-hidden="true"></i></a>
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
        
        <script src="js/funciones.js"></script>
        <%
            }
        %>
    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
