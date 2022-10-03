<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Large Size Online Shop | Products</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="/scripts/controllers/main/productsCategoryController.js"></script>
</head>
<%
    Long dataId = (Long) request.getAttribute("dataId");
%>
<body ng-app="onlineShopApp" ng-controller="productsCategoryCtrl" ng-init="init(<%=dataId%>)">
<jsp:include page="partial/nav.jsp"/>
<br/>
<br/>
<div class="container-fluid">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">
            <h1 class="text-center">{{category.title}}
            <a href="/products" class="btn btn-sm btn-primary float-start">Return</a>
            </h1>
            <br/>
            <br/>
            <div class="container">
                <div class="row row-cols-1 row-cols-md-3 g-4 m-b-20 ">
                    <div class="col-md-4 m-b-20" ng-repeat="product in dataList">
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
                                <small class="text-muted"><span>Price: </span>{{product.price}}</small>
                            <a href="/product/{{product.id}}" class="btn btn-sm btn-primary float-end">Add to basket</a>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <br/>
                <div class="container">
                    <nav class="row">
                        <div class="col-3"></div>
                        <div class="col text-center">
                            <ul class="pagination text-center m-auto">
                                <li ng-repeat="i in range(pageCount) track by $index" class="page-item"
                                    aria-current="page">
                                    <span class="page-link" ng-click="changePage($index)">{{($index + 1)}}</span>
                                </li>
                            </ul>
                        </div>
                        <div class="col-3"></div>
                    </nav>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>

</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>