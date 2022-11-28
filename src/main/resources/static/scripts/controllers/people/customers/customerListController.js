app.controller('customerListCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.query = {
        pageSize: 10,
        pageNumber: 0
    };

    $scope.totalCount = 0;
    $scope.pageCount = 0;
    $scope.dataList = [];

    $scope.getDataList = () => {
        let url = 'customer/getAll?pageSize=' + $scope.query.pageSize + '&pageNumber='
            + $scope.query.pageNumber;
        apiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.query.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.query.pageSize > 0)
                $scope.pageCount++;

        }, (error) => {

        }, true);
    }

    $scope.showInvoices = (id) =>{
        debugger;
        $rootScope.user.customerId = id;
        $scope.changeMenu('customer-dashboard');

    }

    $scope.changePage = (pageNumber) => {
        $scope.query.pageNumber = pageNumber;
        $scope.getDataList();
    }
    $scope.range = (max) => {
        return new Array(max);

    }
       $scope.getDataList();
})