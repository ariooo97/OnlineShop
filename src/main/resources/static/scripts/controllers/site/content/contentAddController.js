app.controller('contentAddCrtl', ['$scope', 'apiHandler' ,'textAngularManager', function ($scope, apiHandler, textAngularManager ) {
    $scope.data = {};
    $scope.addData = () => {
        if ($scope.data.key == undefined || $scope.data.key == null || $scope.data.key == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter key!'
            })

            return;
        }
        if ($scope.data.value == undefined || $scope.data.value == null || $scope.data.value == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter value!'
            })

            return;
        }

        apiHandler.callPost('content/add', $scope.data, (response) => {
            $scope.changeMenu('content-list');
        }, (error) => {

        }, true);
    }
}]);