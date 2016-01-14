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
    <title>岭南软件园协会 - 联系</title>
    <jsp:include page="headInclude.html"></jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"></jsp:include>
    <script>
        document.getElementById("lnc-nav-contact").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>联系我们</h1>
        <p>欢迎校友通过以下方式联系我们</p>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-2">
            <div class="thumbnail">
                <img data-src="holder.js/128x128">
                <div class="caption">
                    <h5>QQ</h5>
                    <p>QQ群只接受同校同学</p>
                </div>
            </div>
        </div>
        <div class="col-sm-4 col-md-3 col-lg-2">
            <div class="thumbnail">
                <img data-src="holder.js/128x128">
                <div class="caption">
                    <h5>Github</h5>
                    <p>会长的Github地址</p>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footInclude.html"></jsp:include>
</body>
</html>
