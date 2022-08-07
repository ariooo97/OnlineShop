app.controller('userEditCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.editData = () => {

        if ($scope.data.firstName == undefined || $scope.data.firstName == null || $scope.data.firstName == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter first name!'
            })

            return;
        }
        if ($scope.data.lastName == undefined || $scope.data.lastName == null || $scope.data.lastName == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter last name!'
            })

            return;
        }

        if ($scope.data.email == undefined || $scope.data.email == null || $scope.data.email == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter email!'
            })

            return;
        }

        if ($scope.data.enable == undefined || $scope.data.enable == null) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please set enable!'
            })

            return;
        }
        apiHandler.callPut('user/', $scope.data, (response) => {
            $scope.changeMenu('user-list');
        }, (error) => {

        }, true);
    }
    $scope.getData = () => {
        apiHandler.callGet("user/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (onerror) => {

        }, true);
    }
    $scope.getData();
})