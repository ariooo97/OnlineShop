<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Online Shop App | panel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="libs/angular.min.js"></script>
    <script src="libs/jquery.min.js"></script>
    <script src="libs/angular-cookies.js"></script>
    <link href="libs/fontawesome-web/css/all.min.css" rel="stylesheet"/>
    <script src="scripts/app.js"></script>
    <script src="scripts/controllers/panelController.js"></script>
    <script src="scripts/controllers/site/navListController.js"></script>
    <script src="scripts/services/ApiHandler.js"></script>
    <link href="libs/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="styles/panel.css" rel="stylesheet"/>
    <script src="libs/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>


</head>
<body ng-app="onlineShopApp">
<div class="container-fluid" ng-controller="panelCtrl">
    <div class="row">
        <div class="col p-0">
            <div class="panel-header">
                <a href="/logout" class="btn btn-danger btn-sm ">Logout</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-2 p-0">
            <div class="slide-nav">
                <div class="text-center p-2">
                    <img src="images/useravatar.webp" width="50"/>
                    <br/>
                    <span>{{user.fullName}}</span>
                </div>
                <ul>
                    <li ng-class="{'slide-nav-active':templateGroup=='dashboard'}">
                        <a href="#" ng-click="changeMenu('dashboard')" >
                            <i class="fa fa-dashboard"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-class="{'slide-nav-active':templateGroup=='nav'}">
                        <a href="#" ng-click="changeMenu('nav-list')" >
                            <i class="fa fa-link"></i>
                            <span>Navigations</span>
                        </a>
                    </li>
                    <li ng-class="{'slide-nav-active':templateGroup=='content'}">
                        <a href="#">
                            <i class="fa fa-file"></i>
                            <span>Content</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-photo-video"></i>
                            <span>Sliders</span>
                        </a>
                    </li>
                    <li ng-class="{'slide-nav-active':templateGroup=='blog'}">
                        <a href="#">
                            <i class="fa fa-newspaper"></i>
                            <span>Blog</span>
                        </a>
                    </li>
                    <li ng-class="{'slide-nav-active':templateGroup=='product'}">
                        <a href="#">
                            <i class="fa fa-cubes-stacked"></i>
                            <span>Products</span>
                        </a>
                    </li>
                    <li ng-class="{'slide-nav-active':templateGroup=='user'}">
                        <a href="#">
                            <i class="fa fa-users"></i>
                            <span>Users</span>
                        </a>
                    </li>
                    <li ng-class="{'slide-nav-active':templateGroup=='customer'}">
                        <a href="#">
                            <i class="fa fa-shopping-bag"></i>
                            <span>Customers</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col p-0">
            <div class="main-container" ng-include="template"></div>
        </div>
    </div>
</div>
</body>
</html>