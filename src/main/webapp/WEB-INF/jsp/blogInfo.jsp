<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Large Size Online Shop | Blog</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="/scripts/controllers/main/blogInfoController.js"></script>
</head>
<%
    Long dataId = (Long) request.getAttribute("dataId");
%>
<body ng-app="onlineShopApp" ng-controller="blogInfoCtrl" ng-init="init(<%=dataId%>)">
<jsp:include page="partial/nav.jsp"/>
<br/>
<br/>
<div class="container-fluid">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">
            <div class="card text-center">
                <div class="card-header">
                    <h1>{{data.title}} <a href="/blog" class="btn btn-sm btn-primary float-end">Return</a></h1>

                </div>
                <div class="card-body">
                    <h3>{{data.subtitle}}</h3>
                    <img width="50%" src="/api/utils/upload/files/{{data.image}}"/>
                    <br/>
                    <hr/>
                    <p class="card-text" ng-bind-html="data.description"></p>
                </div>
                <div class="card-footer text-muted">
                    <i class="fa fa-calendar"></i>
                    <span>{{data.addDateStr}}</span>
                    &nbsp;
                    <i class="fa fa-eye"></i>
                    <span>{{data.visitCount}}</span>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>

</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>