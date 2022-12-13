<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Online Shop App | Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="libs/angular.min.js"></script>
    <script src="libs/jquery.min.js"></script>
    <script src="libs/angular-cookies.js"></script>
    <script src="libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <link rel="stylesheet" href="libs/sweetalert2/dist/sweetalert2.min.css">
    <link href="libs/textAngular-1.5.16/dist/textAngular.css" rel="stylesheet"/>
    <link  rel="stylesheet" href="libs/fontawesome-web/css/fontawesome.min.css"/>
    <link  rel="stylesheet" href="libs/fontawesome-web/css/regular.min.css"/>
    <link  rel="stylesheet" href="libs/fontawesome-web/css/solid.min.css"/>
    <script src="libs/textAngular-1.5.16/dist/textAngular-rangy.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular-sanitize.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular.min.js"></script>
    <script src="scripts/app.js"></script>
    <script src="scripts/services/mainApiHandler.js"></script>
    <script src="scripts/controllers/main/navController.js"></script>
    <script src="scripts/controllers/loginController.js"></script>
    <script src="scripts/services/ApiHandler.js"></script>
    <link href="libs/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="libs/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
    <link href="styles/login.css" rel="stylesheet">


</head>
<body ng-app="onlineShopApp">
<jsp:include page="partial/nav.jsp"/>
<div class="container-fluid" ng-controller="loginCtrl">
        <div class="row">
        <div class="col-3"></div>
        <div class="col login-box-holder">
            <form>
                <h3>Login To Panel</h3>
                <div class="form-group">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" ng-model="user.userName">
                </div>
                <div class="form-group">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" autocomplete="false"
                           ng-model="user.password">
                                   </div>
                <br/>
                <button type="submit" class="btn btn-primary" ng-click="doLogin()">Login</button>
                <br/>
                <div id="emailHelp" class="form-text" style="cursor: pointer" ng-click="doRegister()">Do not have an account?</div>
            </form>
        </div>
        <div class="col-3"></div>
    </div>
</div>
</body>
</html>