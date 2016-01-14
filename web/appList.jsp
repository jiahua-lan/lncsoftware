<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/1/11
  Time: 下午9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园协会 - App</title>
    <jsp:include page="headInclude.html"></jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"></jsp:include>
    <script>
        document.getElementById("lnc-nav-app").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>App平台</h1>
        <p>这里是托管于此的App的入口</p>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-7 col-lg-8 col-sm-6">
                <div class="lnc-marginBox" id="lnc-appList">
                    <div class="media">
                        <div class="media-left">
                            <img data-src="holder.js/64x64">
                        </div>
                        <div class="media-body">
                            <a href="#"><h4>Ouji Yudis</h4></a>
                            <p>Lipahilk Okliudo<br><small>app.lncsoftware.cn/</small></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-5 col-lg-4 col-sm-6">
                <div class="lnc-marginBox">
                    <div class="panel panel-primary">
                        <div class="panel-heading">说明</div>
                        <div class="panel-body">空白</div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">通知</div>
                        <div class="panel-body">空白</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footInclude.html"></jsp:include>
</body>
</html>
