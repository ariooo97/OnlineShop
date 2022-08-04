app.controller('sliderAddCrtl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.addData = () => {
        debugger;
        $scope.data.image=$rootScope.uploadedFile;
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
        if ($scope.data.image == undefined || $scope.data.image == null || $scope.data.image=='') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please upload an image!'
            })

            return;
        }
        apiHandler.callPost('slider/add', $scope.data, (response) => {
            $scope.changeMenu('slider-list');
        }, (error) => {

        }, true);
    }
})