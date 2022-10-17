app.controller('paymentCtrl', function ($scope, mainApiHandler, $rootScope, $cookies) {

    $rootScope.page="Payment";

    $scope.dataList=[];
    $scope.totalPrice=0;
    $scope.countPrice=0;
    $scope.totalCount=0;
    $scope.data={};

    $scope.loadOrderItemList = () => {
        if($cookies.get("basket") == null ||
            $cookies.get("basket") == undefined){
            $scope.dataList=[];
            return;
        }
        $scope.dataList=JSON.parse($cookies.get("basket"));
        debugger;
        for(let i=0; i<$scope.dataList.length;i++){
            $scope.totalCount +=$scope.dataList[i].count;
            $scope.totalPrice +=($scope.dataList[i].price)*($scope.dataList[i].count);
        }

    }


    $scope.goToPayment = () => {
        let orderItems=[];
        for (let i=0; i<$scope.dataList.length;i++){
            let item=$scope.dataList[i];
            orderItems.push({
                productId: item.productId,
                colorId : item.colorId,
                sizeId: item.sizeId,
                count : item.count
            })
        }
        if(orderItems.length==0){
            swal.fire("Your basket is empty");
            return;
        }
        debugger;
        let paymentVM={

            customer :$scope.data,
            orderItems: orderItems
        };
        mainApiHandler.callPost('payment/',paymentVM,(response)=>{
            debugger;
        let href=response.dataList[0].location;
        location.href=href;
        },(err)=>{});

    }

    $scope.loadOrderItemList();

});