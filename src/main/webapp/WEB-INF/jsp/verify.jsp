<%@ page import="org.hibernate.Transaction" %>
<%@ page import="ir.largesize.OnlineShop.entities.orders.Transactions" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html lang="en-US">

<head>
    <title>Large Size Online Shop | Verify</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="scripts/controllers/main/verifyController.js"></script>

    <%
        Transactions transaction = (Transactions) request.getAttribute("transaction");
    %>

</head>
<body ng-app="onlineShopApp" ng-controller="verifyCtrl">
<jsp:include page="partial/nav.jsp"/>

<div class="container-fluid min-height-500">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">
            <div class="card text-center">
                <div class="card-header">
                    Payment Verify
                </div>
                <div class="card-body">
                    <p class="card-text">
                        Amount : <%=transaction.getAmount()%>
                        <br/>
                        Status : <% if (transaction.getRefId() != 0) {%>
                        <span class="badge bg-success">Successfully</span>
                        <br/>
                        <b>Reference Id : <%=transaction.getRefId()%>
                        </b>
                        <% } else {%>
                        <span class="badge bg-danger">Failed</span>
                        <% } %>
                        <br/>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>

</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>