<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Online Shop App | login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="libs/angular.min.js"></script>
    <script src="libs/jquery.min.js"></script>
    <script src="libs/angular-cookies.js"></script>
    <script src="scripts/app.js"></script>
    <script src="scripts/controllers/loginController.js"></script>
    <script src="scripts/services/ApiHandler.js"></script>
    <link href="libs/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="libs/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
    <link href="styles/login.css" rel="stylesheet">


</head>
<body ng-app="onlineShopApp">
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
                    <div id="emailHelp" class="form-text">Do not share your username and password</div>
                </div>
                <button type="submit" class="btn btn-primary" ng-click="doLogin()">Login</button>
            </form>
        </div>
        <div class="col-3"></div>
    </div>
</div>
</body>
</html>