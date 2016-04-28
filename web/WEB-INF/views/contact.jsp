<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午7:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>联系方式</title>
    <jsp:include page="temps/includeCSS.jsp"/>
    <jsp:include page="temps/includeScript.jsp"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="temps/homepageHead.jsp"/>
        <script>document.getElementById("page-head-contact").setAttribute("class","active")</script>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <h2>联系我们</h2>
                <jsp:include page="widgets/contactListWidget.jsp"/>
            </div>
            <div class="col-sm-6">
                <h2>友情链接</h2>
                <jsp:include page="widgets/friendLinksWidget.jsp"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
