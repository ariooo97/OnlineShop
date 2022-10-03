<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Large Size Online Shop | Product</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="/scripts/controllers/main/productInfoController.js"></script>
    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>
</head>
<body ng-app="onlineShopApp" ng-controller="productInfoCtrl" ng-init="init(<%=dataId%>)">
<jsp:include page="partial/nav.jsp"/>
<br/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-6">
            <img width="100%" src="/api/utils/upload/files/{{data.image}}"/>
        </div>
        <div class="col">
            <div class="card ">
                <div class="card-header">
                    <h1>{{data.title}}</h1>
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <label for="colors" class="form-label">Colors</label>
                        <div id="colors" ng-repeat="item in data.colorsList">
                            <div class="color-entity-box" style="background: {{item.value}}"></div>
                            <div class="color-title">
                                <input type="radio" name="color" id="color_{{item.id}}" ng-value="{{item.id}}"
                                       ng-model="orderItem.colorId"/>
                                <lable for="color_{{item.id}}">{{item.name}}</lable>
                            </div>
                        </div>
                        </select>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label for="sizes">Size</label>
                        <select class="form-control"  id="sizes" ng-model="orderItem.sizeId">
                            <option ng-repeat="item in data.sizesList" value="{{item.id}}">{{item.title}}</option>
                        </select>
                    </div>
                    <br/>
                    <a class="btn btn-primary" ng-click="addToBasket()">+ Add to basket</a>
                    <br/>
                    <hr/>
                    <p class="card-text" ng-bind-html="data.description"></p>
                    <ul>
                        <li ng-repeat="feature in data.featuresDataList">
                            {{feature.key}} {{feature.value}}
                        </li>
                    </ul>
                    <br/>
                    <li>Price: {{data.price}}</li>

                </div>
                <div class="card-footer text-muted">
                    <i class="fa fa-eye"></i>
                    <span>{{data.visitCount}}</span>
                    &nbsp
                    <i class="fa fa-calendar"></i>
                    <span>{{data.addDateStr}}</span>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>