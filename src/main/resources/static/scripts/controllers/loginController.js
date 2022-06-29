app.controller("loginCtrl", function ($scope, apiHandler, $cookies) {
    $scope.user = {};
    $scope.doLogin = () => {

        apiHandler.callPost(
            'user/login',
            $scope.user,
            (response) => {
                var token=response.datalist[0].token;
                $cookies.put("userToken",token);
                if(token==null || token==""){
                    alert("invalid token");
                    return;
                }
                $cookies.put("uerToken",token);
                location.herf="/panel";
            }, (error) => {
            });
    }

});
