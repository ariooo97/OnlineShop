<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Large Size Online Shop | Basket</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="scripts/controllers/main/basketController.js"></script>
</head>
<body ng-app="onlineShopApp" ng-controller="basketCtrl">
<jsp:include page="partial/nav.jsp"/>

<div class="container-fluid">
    <br/>
    <div class="row min-height-500">
        <div class="col-1"></div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    Shopping Basket
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Item</th>
                            <th scope="col">Color</th>
                            <th scope="col">Size</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-show="dataList.length == 0">
                            <td colspan="7" class="text-center">
                                <h3>Your Basket Is Empty</h3>
                            </td>
                        </tr>
                        <tr ng-repeat="data in dataList">
                            <th scope="row">{{$index + 1}}</th>
                            <td>
                                <a title="Show Details" target="_blank" href="/product/{{data.productId}}">
                                    <img width="60" src="/api/utils/upload/files/{{data.product.image}}"/>
                                    <span>{{data.product.title}}</span>
                                </a>
                            </td>
                            <td>
                                <div class="color-entity-box" style="background: {{data.color.value}}"></div>
                                <span class="color-title">  {{data.color.name}}</span>
                            </td>
                            <td>{{data.size.title}}</td>
                            <td>{{data.count}}</td>
                            <td>{{data.price}}</td>
                            <td>
                                <a title="Remove" ng-click="removeItem(data)"><i
                                        class="fa fa-times color-red cursor-pointer"></i></a>
                            </td>
                        </tr>
                        </tbody>
                        <tfoot ng-show="dataList.length>0" class="table-footer">
                        <tr>
                            <td colspan="3">#</td>
                            <td><b>Total</b></td>
                            <td>
                                <b>{{totalCount}}</b>
                            </td>
                            <td>
                                <b>{{totalPrice}}</b>
                            </td>
                            <td></td>
                        </tr>
                        </tfoot>
                    </table>
                    <br/>
                    <a ng-show="dataList.length>0" href="/payment" class="btn btn-success"><i
                            class="fa fa-credit-card"></i> Proceed to payment</a>
                    <a href="/products" class="btn btn-outline-primary"><i class="fa fa-basket-shopping"></i> Continue
                        shopping</a>

                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>

</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>