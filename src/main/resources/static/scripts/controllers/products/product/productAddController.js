
app.controller('productAddCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.category=$rootScope.category;
    $scope.colors = [];
    $scope.sizes = [];
    $scope.addData = () => {
        $scope.data.categoryId = $scope.category.id;
        $scope.data.image = $rootScope.uploadedFile;

        if ($scope.data.title == undefined || $scope.data.title == null || $scope.data.title == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter title!'
            })

            return;
        }
        if ($scope.data.price == undefined || $scope.data.price == null || $scope.data.price == "") {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please enter price!'
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
        if ($scope.data.exists == undefined || $scope.data.exists == null) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please set exists!'
            })

            return;
        }
        if ($scope.data.image == undefined || $scope.data.image == null || $scope.data.image == '') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Please upload an image!'
            })

            return;
        }
        apiHandler.callPost('product/add', $scope.data, (response) => {
            $scope.changeMenu('product-list');
        }, (error) => {

        }, true);
    }
    $scope.changeMenuWhitCategory = (template) => {
        $rootScope.category = $Scope.category;
        $scope.changeMenu(template);

    }
    $scope.getColors = () => {
        apiHandler.callGet('color/', (response) => {
            $scope.colors = response.dataList;
        }, (error) => {

        }, true)
    }
    $scope.getSizes = () => {
        apiHandler.callGet('size/', (response) => {
            $scope.sizes = response.dataList;
        }, (error) => {

        }, true)
    }
    $scope.getColors();
    $scope.getSizes();
})