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
    <title>Management Center</title>
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
            <div class="alert alert-warning">Please <a class="alert-link" href="../index.jsp">Login</a></div>
            <%
                    break;

                case "permission denied":
            %>
            <div class="alert alert-danger">Permission denied.</div>
            <%            break;

                case "permission recognised":
                    break;
            }

                if(passport != null && statusFlag.equals("permission recognised")){
            %>
            <div class="panel panel-primary">
                <div class="panel-heading">Management</div>
                <div class="list-group">
                    <a class="list-group-item" href="UserManagement.jsp"><span class="glyphicon glyphicon-user"></span> User</a>
                    <a class="list-group-item" href="AppInfoManagement.jsp"><span class="glyphicon glyphicon-link"></span> Application</a>
                    <a class="list-group-item" href="ArticleManagement.jsp"><span class="glyphicon glyphicon-edit"></span> Article</a>
                    <a class="list-group-item" href="BulletinManagement.jsp"><span class="glyphicon glyphicon-bullhorn"></span> Bulletin</a>
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
