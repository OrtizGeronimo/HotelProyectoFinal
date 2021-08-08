<html lang="es">

    <head>
        <title>Error</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/exito.css">
    </head>
    
    <div class='content'>
        <i class="fa fa-times-circle symbol" aria-hidden="true"></i>
        <div class='title'>Error: no hay más habitaciones <%= request.getSession().getAttribute("tipo")%> disponibles, seleccione otro tipo de habitación o reserve en otra fecha.</div>
        <a href="inicio.jsp" class="volver">Volver</a>
        <div class='text'></div>
    </div>
</body>
</html>
