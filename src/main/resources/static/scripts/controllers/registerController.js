app.controller('registerCtrl', function ($scope, $http, apiHandler, $rootScope) {

    $rootScope.page = "Register";


    $scope.data = {};
    $scope.doRegister = () => {
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


        apiHandler.callPost('user/register', $scope.data, (response) => {
            Swal.fire({
                icon: 'success',
                title: 'registration completed',
                showConfirmButton: true,
                timer: 999999
            }).then((result) => {
                if (result.isConfirmed) {
                    location.href = "/panel"
                }
            })
        }, (error) => {

        }, true);
    }
})