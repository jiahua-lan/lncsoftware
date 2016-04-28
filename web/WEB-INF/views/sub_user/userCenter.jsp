<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User center</title>
    <jsp:include page="../temps/includeCSS.jsp"/>
    <jsp:include page="../temps/includeScript.jsp"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="../temps/homepageHead.jsp"/>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-8">
                <jsp:include page="widget/userContactWidget.jsp"/>
                <jsp:include page="widget/updatePasswordWidget.jsp"/>
            </div>
            <div class="col-md-4">
                <jsp:include page="../widgets/userWidget.jsp"/>
                <script>document.getElementById("widget-user-center").setAttribute("class", "list-group-item active")</script>
            </div>
        </div>
    </div>
</div>
</body>
</html>
