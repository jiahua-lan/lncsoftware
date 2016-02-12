<%@ page import="cn.lncsoftware.data.User" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园 管理中心</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
</head>
<%
    User passport = (User)session.getAttribute("passport");
    String statusFlag = "";

    if(passport == null){
        statusFlag = "no login";
    }else{
        boolean adminFlag = false;
        for(String s : passport.getRights()){
            if(s.equals("admin")){
                adminFlag = true;
                break;
            }
        }
        if(!adminFlag){
            statusFlag = "permission denied";
        }else{
            statusFlag = "permission recognised";
        }
    }
%>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <%
                switch (statusFlag){
                    case "no login":
            %>
            <div class="alert alert-warning">请先 <a class="alert-link" href="../index.jsp">登录</a></div>
            <%
                    break;

                case "permission denied":
            %>
            <div class="alert alert-danger">抱歉，你没有权限</div>
            <%            break;

                case "permission recognised":
                    break;
            }

                if(passport != null && statusFlag.equals("permission recognised")){
            %>
            <div class="panel panel-primary">
                <div class="panel-heading">管理页面</div>
                <div class="list-group">
                    <a class="list-group-item" href="UserManagement.jsp"><span class="glyphicon glyphicon-user"></span> 用户管理</a>
                    <a class="list-group-item" href="AppInfoManagement.jsp"><span class="glyphicon glyphicon-link"></span> 应用链接</a>
                    <a class="list-group-item" href="ArticleManagement.jsp"><span class="glyphicon glyphicon-edit"></span> 文章管理</a>
                    <a class="list-group-item" href="BulletinManagement.jsp"><span class="glyphicon glyphicon-bullhorn"></span> 公告设置</a>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
