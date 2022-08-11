app.controller('colorAddCtrl', function ($scope, apiHandler) {
    $scope.data = {};
    $scope.addData = () => {
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
                text: 'Please enter color value!'
            })

            return;
        }

        apiHandler.callPost('color/add', $scope.data, (response) => {
            $scope.changeMenu('color-list');
        }, (error) => {

        }, true);
    }
})