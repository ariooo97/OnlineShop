<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Large Size Online Shop | Register</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="scripts/controllers/main/paymentController.js"></script>
</head>
<body ng-app="onlineShopApp" ng-controller="paymentCtrl">
<jsp:include page="partial/nav.jsp"/>

<div class="container-fluid">
    <br/>
    <div class="row min-height-500">
        <div class="col-1"></div>
                <div class="col-md-5">
            <br/>
            <div class="card">
                <div class="card-header">
                    User Information
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <input type="text" placeholder="first name" class="form-control" id="firstname"
                                       ng-model="data.firstName">
                            </div>
                            <br/>
                            <div class="form-group">
                                <input type="text" placeholder="last name" class="form-control" id="lastname"
                                       ng-model="data.lastName">
                            </div>
                            <br/>
                            <div class="form-group">
                                <input type="text" placeholder="username" class="form-control" id="username"
                                       ng-model="data.userName">
                            </div>
                            <br/>
                            <div class="form-group" ng-show="!userLoggedIn">
                                <input type="password" placeholder="Password" class="form-control" id="password"
                                       ng-model="data.password">
                            </div>
                            <br/>
                            <div class="form-group">
                                <input type="email" placeholder="Email" class="form-control" id="email"
                                       ng-model="data.email">
                            </div>
                            <br/>
                            <div class="form-group">
                                <input type="text" placeholder="Mobile" class="form-control" id="mobile"
                                       ng-model="data.mobile">
                            </div>
                            <br/>
                            <div class="form-group">
                                <input type="tel" placeholder="Telephone" class="form-control" id="tel"
                                       ng-model="data.tel">
                            </div>
                            <br/>
                            <div class="form-group">
                                    <textarea class="form-control" placeholder="Address" ng-model="data.address"
                                              id="address"></textarea>
                            </div>
                            <br/>
                                                  </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>

    <jsp:include page="partial/footer.jsp"/>
</body>
</html>