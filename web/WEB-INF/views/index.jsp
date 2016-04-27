<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/24
  Time: 下午8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Lingnan Software(Center) Association</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<script src="js/tools.js"></script>
<div class="container">
    <div class="page-header">
        <jsp:include page="temps/homepageHead.jsp"/>
        <script>document.getElementById("page-head-home").setAttribute("class","active")</script>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-8 col-sm-8">
                <jsp:include page="widgets/topBoardWiget.jsp"/>
                <div class="list-group">
                    <jsp:include page="widgets/articleWidget.jsp"/>
                    <s:if test="#request.articleList==null">
                        <div class="list-group-item disabled">No Article.</div>
                    </s:if>
                    <s:elseif test="articleList.size>=10">
                        <a class="list-group-item" href="javascript:">More...</a>
                    </s:elseif>
                </div>
            </div>
            <div class="col-sm-4 col-sm-4">
                <jsp:include page="widgets/userWidget.jsp"/>
            </div>
        </div>
    </div>
</div>

</body>
</html>
