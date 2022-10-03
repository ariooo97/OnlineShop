app.controller('basketCtrl', function ($scope, mainApiHandler, $rootScope, $cookies) {

    $rootScope.page="Basket";

    $scope.dataList=[];
    $scope.totalPrice=0;
    $scope.countPrice=0;
    $scope.totalCount=0;

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

    $scope.removeItem = (data) =>{
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you want to remove this item from basket?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d73636',
            confirmButtonText: 'Yes, remove it!'
        }).then((result) => {
            if (result.isConfirmed) {
                for(let i=0; i<$scope.dataList.length; i++) {
                    if ($scope.dataList[i].id == data.id) {
                        $scope.dataList.splice(i,1);
                    }
                }
                $cookies.put("basket",JSON.stringify($scope.dataList), {path:'/'});
                location.href=location.href;
            }
        })

    }
    $scope.loadOrderItemList();

});