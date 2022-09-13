<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Large Size Online Shop | Products</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="scripts/controllers/main/productsController.js"></script>
</head>
<body ng-app="onlineShopApp" ng-controller="productsCtrl">
<jsp:include page="partial/nav.jsp"/>
<br/>
<div class="container-fluid">
    <div class="row">
        <div class="col-2" ng-repeat="cat in categoryList">
            <a href="products/{{cat.id}}" class="product-cat-item">
                <div class="product-cat-image">
                    <img width="100%" src="/api/utils/upload/files/{{cat.image}}"/>
                </div>
                <h4 class="product-cat-title">{{cat.title}}</h4>
            </a>
        </div>
    </div>
</div>
<br/>
<div>
    <img src="images/jeanProduct.jfif" width="100%"/>
</div>
<br/>
<div class="container-fluid">
    <div class="row">
        <div class="col-1"></div>
        <div class="col-3">
            <div class="list-group">
                <a  class="list-group-item list-group-item-action" ng-click="changeFilter('popular')"
                   ng-class="{'active':selectedFilter=='popular'}">Popular</a>
                <a  class="list-group-item list-group-item-action" ng-click="changeFilter('new')"
                   ng-class="{'active':selectedFilter=='new'}">New</a>
                <a  class="list-group-item list-group-item-action" ng-click="changeFilter('cheapest')"
                   ng-class="{'active':selectedFilter=='cheapest'}">Cheapest</a>
                <a  class="list-group-item list-group-item-action" ng-click="changeFilter('expensive')"
                   ng-class="{'active':selectedFilter=='expensive'}">Expensive</a>
            </div>
        </div>
        <div class="col"></div>
        <div class="col-1"></div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>