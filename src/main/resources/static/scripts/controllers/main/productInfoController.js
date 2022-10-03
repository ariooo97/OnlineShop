app.controller('productInfoCtrl', function ($scope, mainApiHandler, $rootScope, $cookies) {

    $rootScope.page = "Products";

    $scope.dataId = 0;
    $scope.data = {};
    $scope.orderItem = {};
    $scope.orderItemList = [];

    $scope.getProductInfo = () => {
        let url = 'product/info/' + $scope.dataId;
        mainApiHandler.callGet(url, (response) => {
            $scope.data = response.dataList[0];
            $scope.orderItem.colorId = $scope.data.colors[0];
        });
    }



    $scope.init = (id) => {
        $scope.dataId = id;
        $scope.getProductInfo();
    }

    $scope.addToBasket = () => {

        if ($scope.orderItem.sizeId == undefined
            || $scope.orderItem.sizeId == null
            || $scope.orderItem.sizeId == ''
            || $scope.orderItem.sizeId == 0) {
            Swal.fire({
                title: 'Warning',
                text: "Please select a size!",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d73636',
                confirmButtonText: 'Ok'
            });
            return;
        }

        if ($scope.orderItem.colorId == undefined
            || $scope.orderItem.colorId == null
            || $scope.orderItem.colorId == ''
            || $scope.orderItem.colorId == 0) {
            Swal.fire({
                title: 'Warning',
                text: "Please select a color!",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d73636',
                confirmButtonText: 'Ok'
            });
            return;
        }
        $scope.orderItem.productId = $scope.dataId;
        $scope.orderItem.count = 1;
        $scope.orderItem.price = $scope.data.price;
        let existed = false;
        let existedIndex = -1;
        for (let i = 0; i < $scope.orderItemList.length; i++) {
            if ($scope.orderItemList[i].productId == $scope.dataId &&
                $scope.orderItemList[i].sizeId == $scope.orderItem.sizeId &&
                $scope.orderItemList[i].colorId == $scope.orderItem.colorId) {
                existed = true;
                existedIndex = i;
                break;
            }
        }

        if (!existed) {

            $scope.orderItemList.push({
                id: $scope.productId + '_' + $scope.orderItem.colorId + '_' + $scope.orderItem.sizeId,
                productId: $scope.orderItem.productId,
                count: $scope.orderItem.count,
                price: $scope.orderItem.price,
                sizeId: $scope.orderItem.sizeId,
                colorId: $scope.orderItem.colorId,
                product: {
                    image: $scope.data.image,
                    title: $scope.data.title
                },
                color: $scope.data.colorsList.filter(x => x.id == $scope.orderItem.colorId)[0],
                size: $scope.data.sizesList.filter(x => x.id == $scope.orderItem.sizeId)[0]
            });


            Swal.fire({
                title: $scope.data.title,
                text: "Added To Basket",
                icon: 'success',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d73636',
                confirmButtonText: 'Ok'
            });
        } else {
            $scope.orderItemList[existedIndex].count++;
            Swal.fire({
                title: $scope.data.title,
                text: "Added To Basket(" + $scope.orderItemList[existedIndex].count + ")",
                icon: 'success',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d73636',
                confirmButtonText: 'Ok'
            });
        }
        $cookies.put("basket", JSON.stringify($scope.orderItemList), {path: '/'});
    }

    $scope.loadOrderItemList = () => {
        if ($cookies.get("basket") == null ||
            $cookies.get("basket") == undefined) {
            $scope.orderItemList = [];
            return;
        }
        $scope.orderItemList = JSON.parse($cookies.get("basket"));

    }
    $scope.loadOrderItemList();
});