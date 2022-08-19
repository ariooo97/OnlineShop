app.controller('navCtrl',function ($scope,mainApiHandler){
$scope.navList=[];
    
$scope.getNavData = () => {
    mainApiHandler.callGet('nav/', (response) => {
        $scope.navList = response.dataList;
    });
}
        $scope.getNavData();

});