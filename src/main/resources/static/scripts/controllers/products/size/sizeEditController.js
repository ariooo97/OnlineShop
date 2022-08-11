app.controller('sizeEditCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.editData = () => {
        if ($scope.data.title == undefined || $scope.data.title == null || $scope.data.title == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter size title!'
            })
            return;
        }

        apiHandler.callPut('size/', $scope.data, (response) => {
            $scope.changeMenu('size-list');
        }, (error) => {

        }, true);
    }
    $scope.getData = () => {
        apiHandler.callGet("size/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (onerror) => {

        }, true);
    }
    $scope.getData();
})