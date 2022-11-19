app.controller('customerEditCtrl', function ($scope, apiHandler, $rootScope) {

    $rootScope.page="Customer";

    $scope.data={};


    $scope.getUserInfo = () => {
        apiHandler.callGet('user/getUserInfo', (response) => {
            $scope.data = response.dataList[0];
            $scope.data.mobile = $scope.data.customer.mobile;
            $scope.data.address = $scope.data.customer.address;
            $scope.data.tel = $scope.data.customer.tel;
            $scope.data.postalCode = $scope.data.customer.postalCode;

        }, (error) => {

        }, true)
    }

    $scope.editData = () => {
        let customerVM={
             id : $scope.data.customerId,
             firstName: $scope.data.firstName,
             lastName: $scope.data.lastName,
             mobile: $scope.data.mobile,
             tel: $scope.data.tel,
             address: $scope.data.address,
             postalCode: $scope.data.postalCode,
             email: $scope.data.email,
             userName: $scope.data.userName,
             password: $scope.data.password,
        };
        apiHandler.callPut('customer/updateInfo', customerVM,(response)=>{
            swal.fire('Your data has been updated');
            $scope.changeMenu('customer-dashboard');
        },(error)=>{},true);
    }
    $scope.getUserInfo();

});