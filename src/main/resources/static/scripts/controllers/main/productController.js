app.controller('productCtrl',function ($scope,mainApiHandler){
$scope.newProductList=[];
    
$scope.getNewProductData = () => {

    mainApiHandler.callGet('product/newProducts', (response) => {
        debugger;
        $scope.newProductList = response.dataList;
    });
}
        $scope.getNewProductData();

});