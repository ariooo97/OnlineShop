app.controller('navAddCrtl', function ($scope, apiHandler) {
    $scope.data = {};
    $scope.addData = () => {
        if ($scope.data.title == undefined || $scope.data.title == null || $scope.data.title == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter title!'
            })

            return;
        }
        if ($scope.data.link == undefined || $scope.data.link == null || $scope.data.link == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter link!'
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
        apiHandler.callPost('nav/add', $scope.data, (response) => {
            $scope.changeMenu('nav-list');
        }, (error) => {

        }, true);
    }
})