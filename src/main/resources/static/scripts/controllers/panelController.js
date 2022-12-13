app.controller("panelCtrl", function ($scope, apiHandler, $cookies, $rootScope) {
    $scope.template = "";
    $scope.templateName = "";
    $scope.templateGroup = "";


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
    $scope.changeMenu = (templateName) => {
        $scope.templateName = templateName;
        $scope.template = $scope.getMenuPrefix(templateName);
        $scope.templateGroup = $scope.getMenuGroup(templateName);
    }
    $scope.getMenuPrefix = (templateName) => {
        if (templateName === 'dashboard') {
            return 'views/' + templateName + '.html';
        } else if (templateName === 'nav-list' || templateName === 'nav-edit' || templateName === 'nav-add') {
            return 'views/site/nav/' + templateName + '.html';
        } else if (templateName === 'content-list' || templateName === 'content-edit' || templateName === 'content-add') {
            return 'views/site/content/' + templateName + '.html';
        } else if (templateName === 'slider-list' || templateName === 'slider-edit' || templateName === 'slider-add') {
            return 'views/site/slider/' + templateName + '.html';
        } else if (templateName === 'blog-list' || templateName === 'blog-edit' || templateName === 'blog-add') {
            return 'views/site/blog/' + templateName + '.html';
        } else if (templateName === 'user-list' || templateName === 'user-edit' || templateName === 'user-add') {
            return 'views/people/users/' + templateName + '.html';
        } else if (templateName === 'category-list' || templateName === 'category-edit' || templateName === 'category-add') {
            return 'views/products/category/' + templateName + '.html';
        } else if (templateName === 'color-list' || templateName === 'color-edit' || templateName === 'color-add') {
            return 'views/products/color/' + templateName + '.html';
        } else if (templateName === 'size-list' || templateName === 'size-edit' || templateName === 'size-add') {
            return 'views/products/size/' + templateName + '.html';
        } else if (templateName === 'product-list' || templateName === 'product-edit' || templateName === 'product-add') {
            return 'views/products/product/' + templateName + '.html';
        } else if (templateName === 'customer-dashboard' || templateName === 'invoice-detail' || templateName === 'customer-edit') {
            return 'views/customer-dashboard/' + templateName + '.html';
        } else if (templateName === 'uploader') {
            return 'views/util/' + templateName + '.html';
        } else if (templateName === 'customer-list') {
            return 'views/people/customers/' + templateName + '.html';
        } else if (templateName === 'new-order') {
            return 'views/site/order/' + templateName + '.html';
        } else if (templateName === 'all-orders') {
            return 'views/site/order/' + templateName + '.html';
        } else if (templateName === 'newOrderInvoice-detail') {
            return 'views/site/order/' + templateName + '.html';
        } else if (templateName === 'newOrderInvoice-detail') {
            return 'views/site/order/' + templateName + '.html';
        }
    }
    $scope.getMenuGroup = (templateName) => {
        if (templateName === 'dashboard') {
            return 'dashboard';
        } else if (templateName === 'nav-list' || templateName === 'nav-edit' || templateName === 'nav-add') {
            return 'nav';
        } else if (templateName === 'content-list' || templateName === 'content-edit' || templateName === 'content-add') {
            return 'content';
        } else if (templateName === 'slider-list' || templateName === 'slider-edit' || templateName === 'slider-add') {
            return 'slider';
        } else if (templateName === 'blog-list' || templateName === 'blog-edit' || templateName === 'blog-add') {
            return 'blog';
        } else if (templateName === 'user-list' || templateName === 'user-edit' || templateName === 'user-add') {
            return 'user';
        } else if (templateName === 'product-list' || templateName === 'product-edit'
            || templateName === 'product-add' || templateName === 'size-list'
            || templateName === 'size-edit' || templateName === 'size-add'
            || templateName === 'color-list' || templateName === 'color-edit'
            || templateName === 'color-add' || templateName === 'category-list'
            || templateName === 'category-edit' || templateName === 'category-add') {
            return 'product';
        } else if (templateName === 'uploader') {
            return 'uploader';
        } else if (templateName === 'customer-dashboard' || templateName === 'invoice-detail') {
            return 'customer-dashboard';
        } else if (templateName === 'customer-edit') {
            return 'customer-edit';
        } else if (templateName === 'customer-list') {
            return 'customer-list';
        } else if (templateName === 'new-order') {
            return 'new-order';
        } else if (templateName === 'all-orders') {
            return 'all-orders';
        } else if (templateName === 'newOrderInvoice-detail') {
            return 'new-order';
        } else {
            return 'dashboard';

        }
    }

    $scope.logout = () => {
        $cookies.remove("userToken");
        location.href = "/login";
    }

    $scope.init = (cid) => {
        debugger;
        $rootScope.currentCustomerId = cid;
        if (cid == 0 || cid == null || cid == undefined) {
            $scope.changeMenu('dashboard');
        } else {
            $scope.changeMenu('customer-dashboard');
        }
    }


    $scope.checkAccess();
})
;
