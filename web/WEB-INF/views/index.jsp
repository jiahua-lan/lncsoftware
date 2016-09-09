<%@ page import="cn.lncsa.common.RegexTools" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/24
  Time: 下午8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>岭南软件园协会</title>
    <jsp:include page="WEB-INF/views/temps/includeCSS.jsp"/>
    <jsp:include page="WEB-INF/views/temps/includeScript.jsp"/>
    <script src="js/jquery-1.11.3.min.js"></script>
</head>
<body>
<a href="index.jsp"/>
<div class="container">
    <div class="page-header">
        <jsp:include page="/WEB-INF/views/temps/homepageHead.jsp"/>
        <script>document.getElementById("page-head-home").setAttribute("class","active")</script>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-8 col-sm-8">
                <c:if test="#request.topBoardBulletin!=null">
                    <script>$(document).ready(function(){ $("#topBulletinBoard").addClass("animated"); });</script>
                    <div class="bounceIn" id="topBulletinBoard">
                        <jsp:include page="WEB-INF/views/widgets/topBoardWiget.jsp"/>
                    </div>
                </c:if>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Recent Articles
                    </div>
                    <div class="list-group">
                        <jsp:include page="WEB-INF/views/widgets/articleWidget.jsp"/>
                        <c:if test="#request.articleList==null">
                            <div class="list-group-item disabled">没有文章……</div>
                        </c:if>
                        <c:if test="articleList.size>=10">
                            <a class="list-group-item" href="articles">查看更多……</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 col-sm-4">
                <jsp:include page="WEB-INF/views/widgets/userWidget.jsp"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
