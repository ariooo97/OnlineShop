app.controller('sliderListCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.query = {
        pageSize: 10,
        pageNumber: 0
    };
    $scope.totalCount = 0;
    $scope.pageCount = 0;
    $scope.dataList = [];
    $scope.getDataList = () => {
        let url = 'slider/getAll?pageSize=' + $scope.query.pageSize + '&pageNumber='
            + $scope.query.pageNumber;
        apiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.query.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.query.pageSize > 0)
                $scope.pageCount++;

        }, (error) => {

        }, true);
    }

    $scope.changePage = (pageNumber) => {
        $scope.query.pageNumber = pageNumber;
        $scope.getDataList();
    }
    $scope.range = (max) => {
        return new Array(max);

    }
    $scope.editItem = (id) => {
        $rootScope.dataId = id;
        $scope.changeMenu('slider-edit');
    }
    $scope.deleteItem = (id) => {
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
                apiHandler.callDelete('slider/' + id, (response) => {
                    Swal.fire(
                        'Deleted!',
                        'Your data has been deleted.',
                        'success'
                    );
                    $scope.getDataList();
                }, (error) => {

                }, true)

            }

        })
    }
    $scope.changeOrder = (id, direction) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you want change order?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d73636',
            confirmButtonText: 'Yes, change it!'
        }).then((result) => {
            if (result.isConfirmed) {
                apiHandler.callPost('slider/changeOrder/' + id +'/'+direction, null,(response) => {
                    Swal.fire(
                        'Changed!',
                        'Your data has been changed.',
                        'success'
                    );
                    $scope.getDataList();
                }, (error) => {

                }, true)

            }

        })
    }
    $scope.showProducts=(id)=>{
        $rootScope.CategoryId=id;
        $scope.changeMenu('product-list');
    }
    $scope.getDataList();
})