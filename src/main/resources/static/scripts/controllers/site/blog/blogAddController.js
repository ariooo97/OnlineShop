app.controller('blogAddCrtl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.addData = () => {
        $scope.data.image=$rootScope.uploadedFile;
        if ($scope.data.title == undefined || $scope.data.title == null || $scope.data.title == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter title!'
            })

            return;
        }
        if ($scope.data.subtitle == undefined || $scope.data.subtitle == null || $scope.data.subtitle == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter subtitle!'
            })

            return;
        }
        if ($scope.data.description == undefined || $scope.data.description == null || $scope.data.description == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter description!'
            })

            return;
        }
        if ($scope.data.status == undefined || $scope.data.status == null) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please set status!'
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
        if ($scope.data.date == undefined || $scope.data.date == null || $scope.data.date=='') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please upload an image!'
            })

            return;
        }
        apiHandler.callPost('blog/add', $scope.data, (response) => {
            $scope.changeMenu('blog-list');
        }, (error) => {

        }, true);
    }
})