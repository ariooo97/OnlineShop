app.controller('productEditCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.category = $rootScope.Category;
    $scope.colors = [];
    $scope.sizes = [];
    $scope.newFeature = {};
    $scope.data.features = [];
    $scope.featureList = [];
    $scope.selectedColors = [];
    $scope.selectedSizes = [];


    $scope.editData = () => {
        debugger;
        if ($rootScope.uploadedFile != undefined && $rootScope.uploadedFile != null && $rootScope.uploadedFile != "")
            $scope.data.image = $rootScope.uploadedFile;
        $scope.data.categoryId = $rootScope.category.id;

        if ($scope.data.title == undefined || $scope.data.title == null || $scope.data.title == "") {
            Swal.fire('please enter title')
            return;
        }
        if ($scope.data.price == undefined || $scope.data.price == null || $scope.data.price == "") {
            Swal.fire('please enter price')
            return;
        }
        if ($scope.data.exists == undefined || $scope.data.exists == null) {
            Swal.fire('please set exists')
            return;
        }
        if ($scope.data.enable == undefined || $scope.data.enable == null) {
            Swal.fire('please set enable')
            return;
        }
        if ($scope.data.image == undefined || $scope.data.image == null || $scope.data.image == '') {
            Swal.fire('please upload an image')
            return;
        }

        $scope.data.colors = [];
        for (let i = 0; i < $scope.selectedColors.length; i++) {
            $scope.data.colors.push($scope.selectedColors[i]);
        }

        $scope.data.sizes = [];
        for (let i = 0; i < $scope.selectedSizes.length; i++) {
            $scope.data.sizes.push($scope.selectedSizes[i]);
        }

        apiHandler.callPut('product/edit/', $scope.data, (response) => {
            $scope.changeMenuWithCategory('product-list');
        }, (error) => {
        }, true);
    }

    $scope.changeMenuWithCategory = (template) => {
        $rootScope.Category = $scope.category;
        $scope.changeMenu(template);
    }

    $scope.getColors = () => {
        apiHandler.callGet('color/', (response) => {
            $scope.colors = response.dataList;
        }, (error) => {
        }, true);
    }

    $scope.getSizes = () => {
        apiHandler.callGet('size/', (response) => {
            $scope.sizes = response.dataList;
        }, (error) => {
        }, true);
    }

    $scope.addFeature = () => {
        apiHandler.callPost('feature/', $scope.newFeature, (response) => {
            $scope.data.features.push(response.dataList[0].id);
            $scope.featureList.push(response.dataList[0]);
            $scope.newFeature = {};
        }, (error) => {
        }, true);
    }

    $scope.deleteFeature = (id) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
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

    $scope.fillFeatures = () => {
        for (let i = 0; i < $scope.data.featuresDataList.length; i++) {
            $scope.featureList.push($scope.data.featuresDataList[i]);
        }
    }

    $scope.getData = () => {
        apiHandler.callGet("product/info/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
            for (let i = 0; i < $scope.data.colors.length; i++) {
                $scope.selectedColors.push($scope.data.colors[i]);
            }
            for (let i = 0; i < $scope.data.sizes.length; i++) {
                $scope.selectedSizes.push($scope.data.sizes[i]);
            }
            $scope.fillFeatures();
        }, (error) => {
        }, true);
    }

    $scope.isSelected = (list, item) => {
        if (list == undefined) return false;
        return list.some(x => x == item.id);
    }

    $scope.onColorChanged = (color) => {
        if ($scope.data.colors[color.id] && !$scope.selectedColors.some(x => x == color.id)) {
            $scope.selectedColors.push(color.id);
        } else if (!$scope.data.colors[color.id] && $scope.selectedColors.some(x => x == color.id)) {
            for (let i = 0; i < $scope.selectedColors.length; i++) {
                if ($scope.selectedColors[i] == color.id) {
                    $scope.selectedColors.splice(i, 1);
                    return;
                }
            }
        }
    }

    $scope.onSizeChanged = (size) => {
        if ($scope.data.sizes[size.id] && !$scope.selectedSizes.some(x => x == size.id)) {
            $scope.selectedSizes.push(size.id);
        } else if (!$scope.data.sizes[size.id] && $scope.selectedSizes.some(x => x == size.id)) {
            for (let i = 0; i < $scope.selectedSizes.length; i++) {
                if ($scope.selectedSizes[i] == size.id) {
                    $scope.selectedSizes.splice(i, 1);
                    return;
                }
            }
        }
    }

    $scope.getColors();
    $scope.getSizes();
    $scope.getData();
})