app.controller('colorEditCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.editData = () => {
        if ($scope.data.name == undefined || $scope.data.name == null || $scope.data.name == "") {
            Swal.fire({
                icon: 'error',
                name: 'Error',
                text: 'Please enter color name!'
            })
            return;
        }
        if ($scope.data.value == undefined || $scope.data.value == null || $scope.data.value == "") {
            Swal.fire({
                icon: 'error',
                name: 'Error',
                text:'Please enter color value!'
            })
            return;
        }

        apiHandler.callPut('color/', $scope.data, (response) => {
            $scope.changeMenu('color-list');
        }, (error) => {

        }, true);
    }
    $scope.getData = () => {
        apiHandler.callGet("color/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (onerror) => {

        }, true);
    }
    $scope.getData();
})