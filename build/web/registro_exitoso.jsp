<html lang="es">

    <head>
        <title>?xito</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/exito.css">
    </head>
    
    <div class='content'>
        <div class='fa fa-check-circle-o symbol'></div>
        <div class='title'><h3>Reserva realizada con ?xito!</h3></div>
        <div class='title'>Habitaci?n n?mero <%= request.getSession().getAttribute("habitacion")%> - Piso <%=request.getSession().getAttribute("piso")%> </div>
        <div class='title'>Monto total a pagar: $<%= request.getSession().getAttribute("monto")%> </div>
        <a href="inicio.jsp" class="volver">Volver</a>
        <div class='text'></div>
    </div>
</body>
</html>
