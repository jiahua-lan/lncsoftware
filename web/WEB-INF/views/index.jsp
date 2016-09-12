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
    <jsp:include page="/WEB-INF/views/temps/includeCSS.jsp"/>
    <jsp:include page="/WEB-INF/views/temps/includeScript.jsp"/>
    <script src="/js/jquery-1.11.3.min.js"></script>
    <script>
        window.onload = function () {
            document.getElementById("page-head-home").setAttribute("class","active");
        }

        $(document).ready(function(){
            $("#topBulletinBoard").addClass("animated");
        })
    </script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="/WEB-INF/views/temps/homepageHead.jsp"/>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-8 col-sm-8">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Recent Articles
                    </div>
                    <div class="list-group">
                        <div class="list-group-item disabled">没有文章……</div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 col-sm-4">

            </div>
        </div>
    </div>
</div>
</body>
</html>
