app.controller('navCtrl', function ($scope, mainApiHandler, $rootScope) {
    $scope.navList = [];
    $scope.searchKey = "";
    $scope.page = $rootScope.page;

    $scope.getNavData = () => {
        mainApiHandler.callGet('nav/', (response) => {
            $scope.navList = response.dataList;
        });
    }
    $scope.search = () => {
        location.href="/products/search?key="+$scope.searchKey;

    }
    $scope.getNavData();

});