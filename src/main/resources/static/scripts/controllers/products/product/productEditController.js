app.controller('productEditCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.category = $rootScope.category;
    $scope.colors = [];
    $scope.sizes = [];
    $scope.newFeature = {};
    $scope.data.features = [];
    $scope.data.colors = [];
    $scope.data.size = [];
    $scope.featureList = [];
    $scope.selectedColors = [];
    $scope.selectedSizes = [];

    $scope.editData = () => {
        $scope.data.categoryId = $scope.category.id;
        if ($rootScope.uploadedFile != undefined && $rootScope.uploadedFile != null && $rootScope.uploadedFile != "")
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
        for(let i=0;i <$scope.data.colors.length; i++){
            $scope.data.colors.push($scope.selectedColors[i]);
        }

        for(let i=0;i <$scope.data.size.length; i++){
            $scope.data.size.push($scope.selectedSizes[i]);
        }

        apiHandler.callPut('product/edit', $scope.data, (response) => {
            $scope.changeMenu('product-list');
        }, (error) => {

        }, true);
    }
    $scope.changeMenuWhitCategory = (template) => {
        $rootScope.category = $scope.category;
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

                }, true);

            }
        })

    }
    $scope.fillFeature = () => {

        for (let i = 0; i < $scope.data.features.length; i++) {
            $scope.featureList.push($scope.data.features[i]);
        }
    }


    $scope.getData = () => {
        apiHandler.callGet("product/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
            for (let i = 0; i < $scope.data.colors.length; i++) {
                $scope.selectedColors.push($scope.colors[i]);
            }
            for (let i = 0; i < $scope.data.sizes.length; i++) {
                $scope.selectedSizes.push($scope.sizes[i]);
            }
                   $scope.fillFeature();
        }, (onerror) => {

        }, true);
    }

    $scope.isSelected = (list, item) => {
        if (list == undefined) return false;

        return list.some(x => x == item.id);
    }

    $scope.onColorChange = (color) => {

        if ($scope.data.colors[color.id] && !$scope.selectedColors.some(x => x == color.id)) {
            $scope.selectedColors.push(color.id);
        } else if ($scope.data.colors[color.id] && !$scope.selectedColors.some(x => x == color.id)) {
            for (let i = 0; i < $scope.selectedColors.length; i++) {
                if ($scope.selectedColors[i] == color.id) {
                    $scope.data.colors.splice(i, 1);
                    return;
                    ;
                }
            }
        }

    }

    $scope.onSizeChange = (size) => {
        if ($scope.data.sizes[size.id] && !$scope.selectedSizes.some(x => x == size.id)) {
            $scope.selectedSizes.push(size.id);
        } else if ($scope.data.sizes[size.id] && !$scope.selectedSizes.some(x => x == size.id)) {
            for (let i = 0; i < $scope.selectedSizes.length; i++) {
                if ($scope.selectedSizes[i] == size.id) {
                    $scope.data.sizes.splice(i, 1);
                    return;
                    ;
                }
            }
        }
    }

    $scope.getColors();
    $scope.getSizes();
    $scope.getData();
})