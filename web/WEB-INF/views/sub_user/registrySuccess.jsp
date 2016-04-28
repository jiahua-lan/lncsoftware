<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/28
  Time: 上午10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up | LNCSA</title>
    <jsp:include page="../temps/includeCSS.jsp"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="../temps/homepageHead.jsp"/>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-success">
                    <div class="panel-body">
                        <div class="page-header">
                            <h1>Success!</h1>
                        </div>
                        <p><h4>Sign up success!</h4>
                        <b>${username}, please remember your password and username for your first login.</b>
                        </p>
                    </div>
                    <div class="panel-footer">
                        <a class="btn btn-success" href="login"><span class="glyphicon glyphicon-log-in"></span> 登陆</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
