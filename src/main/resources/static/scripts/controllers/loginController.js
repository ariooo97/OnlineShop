app.controller("loginCtrl", function ($scope, apiHandler, $cookies) {
    $scope.user = {};
    $scope.doLogin = () => {
        apiHandler.callPost(
            'user/login',
            $scope.user,
            (response) => {
                var token = response.dataList[0].token;
                $cookies.put("userToken", token);
                if (token == null || token == "") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'invalid token'
                    })
                    return;
                }
                $cookies.put("uerToken", token);

                location.href = "/panel";
            }, (error) => {
            });
    }
});
