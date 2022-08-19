app.service("mainApiHandler", function ($http) {

    this.callGet = (url, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'GET'


        };
        $http(request).then((response) => {

            if (response != null && response.data != null) {
                var result = response.data;

                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.status == "hasError") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: result.message
                    })

                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'unknown error !!!'
                    })
                }
            }
        }, (err) => {
            if(err.status == 417){
                $cookies.remove("userToken");
                location.href = "/login";
                return;
            }
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Exception on server'
            })

            onError(err);
        });
    }
    this.callPost = (url, data, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'POST',
            data: data
        };
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);

                } else if (result.hasError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: result.message
                    })

                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'unknown error (call post)!!!'
                    })

                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Exception on server!!!'
            })

            onError(err);
        });
    }

    this.callPut = (url, data, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'PUT',
            data: data
        };
                $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);

                } else if (result.status == "hasError") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: result.message
                    })

                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'unknown error!!!'
                    })

                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Exception'
            })

            onError(err);
        });
    }

    this.callDelete = (url, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'DELETE'
        };
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);

                } else if (result.status == "hasError") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: result.message
                    })

                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'unknown error!!!'
                    })
                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Exception'
            })

            onError(err);
        });
    }
   });



