app.controller('productsCtrl', function ($scope, mainApiHandler, $rootScope) {

    $rootScope.page="Products";
    $scope.selectedFilter='popular';
    $scope.productsList=[];
    $scope.categoryList = [];

    $scope.getCategoryList = () => {

        mainApiHandler.callGet('productCategory', (response) => {

            $scope.categoryList = response.dataList;
        });
    }

    $scope.getNewProductData = () => {
        mainApiHandler.callGet('product/newProducts', (response) => {
            $scope.productsList = response.dataList;
        });
    }

    $scope.getPopularProductData = () => {
        mainApiHandler.callGet('product/popularProducts', (response) => {
            $scope.productsList = response.dataList;
        });
    }

    $scope.changeFilter =(filter) =>{
    $scope.selectedFilter=filter;
    switch (filter){
        case 'popular':
            break;
        case 'new':
            break;
        case 'cheapest':
            break;
        case 'expensive':
            break;
    }
    }

    $scope.getCategoryList();

});