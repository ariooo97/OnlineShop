app.controller("panelCtrl", function ($scope, apiHandler, $cookies, $rootScope) {

    $scope.checkAccess = () => {
        var token = $cookies.get("userToken");
        if (token == undefined || token == null || token == "") {
            location.href = "/login";
            return;
        }
        $scope.getUserInfo();

    }
    $scope.getUserInfo = () => {
        apiHandler.callGet('user/getUserInfo', (response) => {
            $rootScope.userInfo = response.dataList[0];
            $rootScope.user = $rootScope.userInfo;

        }, (error) => {

        }, true)
    }

    $scope.checkAccess();
});
