app.controller("panelCtrl", function ($scope, apiHandler, $cookies, $rootScope) {
    $scope.template="views/dashboard.html";
    $scope.templateName="dashboard";
    $scope.templateGroup="dashboard";
    $scope.checkAccess = () => {
        var token = $cookies.get("userToken");
        if (token == undefined || token == null || token == "") {
            location.href = "/login";
            return;
        }
        $scope.getUserInfo();

    }
    $scope.getUserInfo = () => {
        apiHandler.callGet('user/getUserInfo', (response) => {
            $rootScope.userInfo = response.dataList[0];
            $rootScope.user = $rootScope.userInfo;

        }, (error) => {

        }, true)
    }
    $scope.changeMenu=(templateName) =>{
        $scope.templateName=templateName;
        $scope.template=$scope.getMenuPrefix(templateName);
        $scope.templateGroup=$scope.getMenuGroup(templateName);
    }
    $scope.getMenuPrefix=(templateName)=>{
        switch (templateName){
            case 'dashboard':
                return 'views/'+templateName+'.html';
            case 'nav-list':
                return 'views/site/nav/'+templateName+'.html';
            default:
                return 'views/dashboard.html';
        }
    }
    $scope.getMenuGroup=(templateName)=>{
        if (templateName==='dashboard') {
            return 'dashboard';
        }else if (templateName==='nav-list' || templateName==='nav-edit' || templateName==='nav-insert'){
            return 'nav';
        }else{
            return 'dashboard';

        }
    }
    $scope.checkAccess();
});
