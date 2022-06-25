app.controller("loginCtrl", function ($scope, $http) {
    $scope.user = {};
    $scope.doLogin = () => {
        debugger;
        console.log($scope.user);
        $http.post('/api/user/login', $scope.user).then((response) => {
            debugger;
        }, (error) => {
            debugger;
        });
    }
});