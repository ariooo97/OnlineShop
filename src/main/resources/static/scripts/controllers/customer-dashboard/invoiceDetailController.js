app.controller('invoiceDetailsCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.dataId = 0;
    $scope.data = {};
    $scope.countPrice=0;
    $scope.totalCount=0;

    $scope.getData = () => {
        let url = 'invoice/getInfo/' + $scope.dataId;
        apiHandler.callGet(url, (response) => {
            for(let i=0; i<$scope.data.orderItems.length;i++){
                $scope.totalCount +=$scope.orderItems[i].count;
                $scope.totalPrice +=($scope.orderItems[i].price)*($scope.orderItems[i].count);
            }
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true);
    }

    $scope.init = () => {
        $scope.dataId = $rootScope.invoiceId;
        $scope.getData();
    }

    $scope.init();

})