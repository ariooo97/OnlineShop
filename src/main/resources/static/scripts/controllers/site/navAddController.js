app.controller('navAddCrtl', function ($scope, apiHandler) {
    $scope.data = {};
    $scope.addData = () => {
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
        apiHandler.callPost('nav/add', $scope.data, (response) => {
            $scope.changeMenu('nav-list');
        }, (error) => {

        }, true);
    }
})