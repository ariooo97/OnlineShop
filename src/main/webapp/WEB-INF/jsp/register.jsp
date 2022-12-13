<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Online Shop App | Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="libs/angular.min.js"></script>
    <script src="libs/jquery.min.js"></script>
    <script src="libs/angular-cookies.js"></script>
    <script src="libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <link rel="stylesheet" href="libs/sweetalert2/dist/sweetalert2.min.css">
    <link href="libs/textAngular-1.5.16/dist/textAngular.css" rel="stylesheet"/>
    <link rel="stylesheet" href="libs/fontawesome-web/css/fontawesome.min.css"/>
    <link rel="stylesheet" href="libs/fontawesome-web/css/regular.min.css"/>
    <link rel="stylesheet" href="libs/fontawesome-web/css/solid.min.css"/>
    <script src="libs/textAngular-1.5.16/dist/textAngular-rangy.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular-sanitize.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular.min.js"></script>
    <script src="scripts/app.js"></script>
    <script src="scripts/services/mainApiHandler.js"></script>
    <script src="scripts/services/apiHandler.js"></script>
    <script src="scripts/controllers/main/navController.js"></script>
    <script src="scripts/controllers/registerController.js"></script>
    <script src="scripts/services/ApiHandler.js"></script>
    <link href="libs/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="libs/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
    <link href="styles/login.css" rel="stylesheet">


</head>
<body ng-app="onlineShopApp">
<jsp:include page="partial/nav.jsp"/>
<br/>
<div class="container-fluid" ng-controller="registerCtrl">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="card col-md-6">
            <div class="card-body">
                <h3>User Registration</h3>
                <br/>
                <div class="row">
                    <div class="col">
                        <form>
                                              <div class="form-group">
                            <label for="firstname" class="form-label">first name</label>
                            <input type="text" class="form-control" id="firstname" ng-model="data.firstName">
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="form-label">last name</label>
                            <input type="text" class="form-control" id="lastname" ng-model="data.lastName">
                        </div>
                            <div class="form-group">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="username" ng-model="data.userName">
                            </div>
                        <div class="form-group">
                            <label for="password" class="form-label ">Password</label>
                            <input type="password" class="form-control" id="password" ng-model="data.password">
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-primary" ng-click="doRegister()">Register</button>
                        <br/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>

