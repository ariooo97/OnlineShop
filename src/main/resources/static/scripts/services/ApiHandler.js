app.service("apiHandler", function ($http, $cookies) {
    this.callGet = (url, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'GET'


        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {

            if (response != null && response.data != null) {
                var result = response.data;

                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.status == "hasError") {
                    alert(result.message);
                } else {
                    alert("unknown error !!!");
                }
            }
            debugger;
        }, (err) => {
            alert("Exception");
            onError(err);
        });
    }
    this.callPost = (url, data, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'POST',
            data: data
        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);

                } else if (result.hasError) {
                    alert(result.message);
                } else {
                    alert("unknown error !!!");
                }
            }
        }, (err) => {
            alert("Exception");
            onError(err);
        });
    }

    this.callPut = (url, data, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'PUT',
            data: data
        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);

                } else if (result.status == "hasError") {
                    alert(result.message);
                } else {
                    alert("unknown error !!!");
                }
            }
        }, (err) => {
            alert("Exception");
            onError(err);
        });
    }

    this.callDelete = (url, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'DELETE'
        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);

                } else if (result.status == "hasError") {
                    alert(result.message);
                } else {
                    alert("unknown error !!!");
                }
            }
        }, (err) => {
            alert("Exception");
            onError(err);
        });
    }
    this.checkAndSetToken = (request, setToken) => {
        if (setToken) {
            let token = $cookies.get("userToken");
            request.headers = {
                'Authorization': 'Bearer ' + token
            };
        }
    }
})