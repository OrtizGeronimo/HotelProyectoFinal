


<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Template</title>
        <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/login.css">
    </head>
    <body>
        <main>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 login-section-wrapper">
                        <div class="brand-wrapper">
                            <h1 class="bienvenida">Bienvenido a Hotel Ortiz!</h1>
                           <!-- <img src="assets/images/logo.svg" alt="logo" class="logo"> -->
                        </div>
                        <div class="login-wrapper my-auto">
                            <h1 class="login-title">Iniciar Sesión</h1>
                            <form action="SvLogin" method="POST">
                                <div class="form-group">
                                    <label for="email">Usuario</label>
                                    <input type="text" name="user" id="email" class="form-control" placeholder="Ingrese usuario">
                                </div>
                                <div class="form-group mb-4">
                                    <label for="password">Contraseña</label>
                                    <input type="password" name="password" id="password" class="form-control" placeholder="Ingresa tu contraseña">
                                </div>
                                <input name="login" id="login" class="btn btn-block login-btn" type="submit" value="Login">
                            </form>
                            <form action="SvAltaAdmin" method="GET">
                                <li><a href="SvAltaAdmin">Generar primer usuario admin automáticamente</a></li>
                                </form>
                           <!-- <p class="login-wrapper-footer-text">Don't have an account? <a href="#!" class="text-reset">Register here</a></p> -->
                        </div>
                    </div>
                    <div class="col-sm-6 px-0 d-none d-sm-block">
                        <img src="assets/images/login.jpg" alt="login image" class="login-img">
                    </div>
                </div>
            </div>
        </main>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
