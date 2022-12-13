app.controller('newOrderInvoiceDetailsCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.dataId = 0;
    $scope.data = {};
    $scope.totalCount = 0;
    $scope.totalPrice = 0;

    $scope.getData = () => {
        let url = 'invoice/getInfo/' + $scope.dataId;
        apiHandler.callGet(url, (response) => {
            debugger;
            $scope.data = response.dataList[0];
            for (let i = 0; i < $scope.data.orderItems.length; i++) {
                $scope.totalCount += $scope.data.orderItems[i].count;
                $scope.totalPrice += (($scope.data.orderItems[i].price) * ($scope.data.orderItems[i].count));
            }

        }, (error) => {

        }, true);
    }

    $scope.invoiceAction = (id) => {
        debugger;
        swal.fire({
            title: 'Are you sure?',
            text: 'Change invoice status to Processed',
            icon: 'success',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, Change it!'
        }).then((result) => {
            debugger;
                       apiHandler.callPut('invoice/update/', id, (response) => {
                debugger;
                $scope.changeMenu('new-order')
            }, (error) => {}, true);
        });
    }

    $scope.init = () => {
        debugger;
        $scope.dataId = $rootScope.customerId;
        $scope.getData();
    }

    $scope.init();

})