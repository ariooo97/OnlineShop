app.controller('navEditCrtl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.editData = () => {
        if ($scope.data.title == undefined || $scope.data.title == null || $scope.data.title == "") {
            alert('Please enter title!');
            return;
        }
        if ($scope.data.link == undefined || $scope.data.link == null || $scope.data.link == "") {
            alert('Please enter link!');
            return;
        }
        if ($scope.data.enable == undefined || $scope.data.enable == null) {
            alert('Please set enable!');
            return;
        }
        apiHandler.callPut('nav/', $scope.data, (response) => {
            $scope.changeMenu('nav-list');
        }, (error) => {

        }, true);
    }
    $scope.getData = () => {
        apiHandler.callGet("nav/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (onerror) => {

        }, true);
    }
    $scope.getData();
})