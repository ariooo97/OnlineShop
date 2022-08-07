app.controller('userAddCrtl', function ($scope, apiHandler) {
    $scope.data = {};
    $scope.addData = () => {
        if ($scope.data.userName == undefined || $scope.data.userName == null || $scope.data.userName == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter Username!'
            })

            return;
        }
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
        if ($scope.data.password == undefined || $scope.data.password == null || $scope.data.password == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter password!'
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
        if ($scope.data.role == undefined || $scope.data.role == null) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please set user role!'
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
        apiHandler.callPost('user/add', $scope.data, (response) => {
            $scope.changeMenu('user-list');
        }, (error) => {

        }, true);
    }
})