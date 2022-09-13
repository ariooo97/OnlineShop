app.controller('productAddCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.category = $rootScope.category;
    $scope.colors = [];
    $scope.sizes = [];
    $scope.newFeature = {};
    $scope.data.features = [];
    $scope.featureList=[];

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

    $scope.addFeature = () => {

        apiHandler.callPost('feature/', $scope.newFeature, (response) => {

            $scope.data.features.push(response.dataList[0].id);
            $scope.featureList.push(response.dataList[0]);
            $scope.newFeature = {};
        }, (error) => {

        }, true)
    }
    $scope.deleteFeature = (id) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d73636',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                apiHandler.callDelete('feature/' + id, (response) => {
                    for (let i = 0; i < $scope.data.features.length; i++) {
                        if ($scope.data.features[i] == id) {
                            $scope.data.features.splice(i, 1);
                            break;
                        }
                    }
                    for (let i = 0; i < $scope.featureList.length; i++) {
                        if ($scope.featureList[i].id == id) {
                            $scope.featureList.splice(i, 1);
                            break;
                        }
                    }

                }, (error) => {

                },true);

            }

        })

    }
    $scope.getColors();
    $scope.getSizes();
})