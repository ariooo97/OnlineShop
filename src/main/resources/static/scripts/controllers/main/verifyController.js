app.controller('verifyCtrl', function ($scope, mainApiHandler, $rootScope, $cookies) {

    $rootScope.page="Verify";
    $scope.init = () =>{
        $cookies.remove("basket");
    }
    $scope.init();
});