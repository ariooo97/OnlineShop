app.controller('sizeAddCtrl', function ($scope, apiHandler) {
    $scope.data = {};
    $scope.addData = () => {
        if ($scope.data.title == undefined || $scope.data.title == null || $scope.data.title == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter size title!'
            })

            return;
        }

        apiHandler.callPost('size/add', $scope.data, (response) => {
            $scope.changeMenu('size-list');
        }, (error) => {

        }, true);
    }
})