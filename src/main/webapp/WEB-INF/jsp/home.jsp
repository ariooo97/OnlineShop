<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Large Size Online Shop | Home</title>
    <jsp:include page="partial/header.jsp"/>
    <link href="libs/slider/square1.min.css" rel="stylesheet"/>
    <link href="libs/slider/square2.css" rel="stylesheet"/>
    <script src="libs/slider/square1.min.js"></script>
    <script src="libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <link rel="stylesheet" href="libs/sweetalert2/dist/sweetalert2.min.css">
    <script src="scripts/controllers/main/sliderController.js"></script>
    <script src="scripts/controllers/main/productController.js"></script>
    <script src="scripts/controllers/main/homeController.js"></script>
</head>

<body ng-app="onlineShopApp" ng-controller="homeCtrl">
<jsp:include page="partial/nav.jsp"/>

<div ng-controller="sliderCtrl">
    <div class="slideshow top-slider">
        <div ng-repeat="slider in sliderList">
            <a href="{{slider.link}}">
                <img src="/api/utils/upload/files/{{slider.image}}" height="400" alt="{{slider.title}}">
                <div class="text_content">
                    <h3>{{slider.title}}</h3>
                    <h5>{{slider.description}}</h5>
                </div>
            </a>
        </div>
    </div>
</div>

<div ng-controller="productCtrl">
    <h1 class="text-center">New Products</h1>
    <br/>
    <div class="container">
        <div class="row row-cols-1 row-cols-md-3 g-4 m-b-20 ">
            <div class="col-md-4 m-b-20" ng-repeat="product in newProductList">
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
    </div>
    <div class="parallax-cover">
    </div>
    <h1 class="text-center m-t-30">Popular Product</h1>
    <br/>
    <div class="container">
        <div class="row">
            <div class="col-md-4 m-b-20" ng-repeat="product in popularProductList">
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
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>

