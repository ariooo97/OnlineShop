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
        <div class="col-md-2" ng-repeat="cat in categoryList">
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
               <div class="col-md-3">
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
                   <br/>
                   <br/>
        </div>
        <div class="col">
            <h1 class="text-center m-t-30">{{selectedTitle}}</h1>
            <br/>
            <div class="container">
                <div class="row">
                    <div class="col-md-4 m-b-20" ng-repeat="product in productsList">
                        <div class="card h-100">
                            <img src="/api/utils/upload/files/{{product.image}}" width="100" height="400"
                                 class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title">{{product.title}}</h5>
                                <div class="card-text">
                                    <ul>
                                        <li ng-repeat="feature in product.featuresDataList">
                                            {{feature.key}}
                                            {{feature.value}}
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">{{product.addDateStr}}</small>
                                <a href="product/{{product.id}}" class="btn btn-sm btn-primary float-end">Add to basket</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div></div>
            </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>