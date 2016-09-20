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
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/bootstrap-markdown.min.css"/>
    <link rel="stylesheet" href="//cdn.bootcss.com/animate.css/3.5.1/animate.css"/>
    <script src="//cdn.bootcss.com/jquery/3.0.0-beta1/jquery.min.js"></script>
    <script src="/resources/js/main.js"></script>
    <script>
        $(document).ready(function () {
            var headNodes = document.getElementById("top-navBar").getElementsByTagName("li");
            for (var i = 0; i < headNodes.length; i++) {
                headNodes[i].index = i;
                headNodes[i].onclick = function () {
                    for (var i = 0; i < headNodes.length; i++) {
                        headNodes[i].className = "";
                    }
                    headNodes[this.index].className = "active";
                }
            }
            LNCSA.mainPage.getListTopBoard($("#topBulletinBoard"));
            document.getElementById("page-head-home").setAttribute("class", "active");
        });
    </script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>岭南软件园协会</h1>
        <p>${bulletin.context}</p>
        <nav>
            <ul class="nav nav-pills" id="top-navBar">
                <li id="page-head-home"><a href="/">主页</a></li>
                <li id="page-head-article"><a href="#">文章</a></li>
                <li id="page-head-apps"><a href="#">应用</a></li>
                <li id="page-head-contact"><a href="#">联系</a></li>
            </ul>
        </nav>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-8 col-sm-8">
                <div class="panel panel-primary bounceIn hidden">
                    <div class="panel-body" id="topBulletinBoard"></div>
                </div>
                <div class="panel panel-default">
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

<script src="/resources/js/holder.min.js"></script>
<script src="/resources/js/tools.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/bootstrap-markdown.js"></script>
<script src="/resources/js/markdown.min.js"></script>
</body>
</html>
