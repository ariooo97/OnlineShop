app.controller('blogInfoCtrl', function ($scope, mainApiHandler, $rootScope) {

    $rootScope.page="Blog";

    $scope.dataId=0;
    $scope.data={};

    $scope.getBlogInfo = () => {
        let url = 'blog/info/'+$scope.dataId;
        mainApiHandler.callGet(url, (response) => {
            $scope.data = response.dataList[0];
                   });
    }

    $scope.init=(id)=>{
        $scope.dataId=id;
        $scope.getBlogInfo();
    }


});