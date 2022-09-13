app.controller('contentCtrl', function ($scope, mainApiHandler) {
    $scope.contentList = [];

    $scope.getContentData = () => {
        mainApiHandler.callGet('content/getAllData', (response) => {
            $scope.contentList = response.dataList;
        });
    }

    $scope.getContent = (key)=>{
        for(let i=0; i<$scope.contentList.length; i++){
            if($scope.contentList[i].key==key)
                return $scope.contentList[i].value;
        }

    }

    $scope.getContentData();

});