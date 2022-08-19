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

</head>
<body ng-app="onlineShopApp">
<jsp:include page="partial/nav.jsp"/>

<div ng-controller="sliderCtrl">
    <div class="slideshow top-slider">
        <div ng-repeat="slider in sliderList">
            <a href="{{slider.link}}">
                <img src="/api/utils/upload/files/{{slider.image}}" alt="{{slider.title}}">
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
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="card " ng-repeat="product in newProductList" >
                <img src="/api/utils/upload/files/{{product.image}}" width="100" class="card-img-top" >
                <div class="card-body">
                    <h5 class="card-title">{{product.title}}</h5>
                    <p class="card-text">
                    <p ng-repeat="feature in product.features"> {{feature.key}} : {{feature.value}}
                        <br/>>
                    </p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">{{product.addDate}}</small>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>
<br/>
<br/>
</body>
</html>