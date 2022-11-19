app.controller("dashboardCtrl", function ($scope, apiHandler) {
    $scope.data = {};

    $scope.getData = () => {
        apiHandler.callGet('dashboard',(response) =>{
            $scope.data= response.dataList[0];
        },(error)=>{},true);

    }

    $scope.getData();
});
