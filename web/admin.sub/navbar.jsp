<%@ page import="cn.lncsoftware.data.User" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/12
  Time: 上午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <div class="navbar-brand">岭南软件园管理</div>
        </div>
        <ul class="nav navbar-nav">
            <%
                User nav_passport = (User)session.getAttribute("passport");
                boolean permission = false;
                if(nav_passport != null){
                    for(String s : nav_passport.getRights()){
                        if("admin".equals(s)) {
                            permission = true;
                            break;
                        }
                    }
                }

                if(permission){
            %>
            <li id="nav-user"><a href="UserManagement.jsp">用户</a></li>
            <li id="nav-article"><a href="ArticleManagement.jsp">文章</a></li>
            <li id="nav-app"><a href="AppInfoManagement.jsp">应用</a></li>
            <li id="nav-bulletin"><a href="BulletinManagement.jsp">公告</a></li>
            <%
                }else{
            %>
            <li><a href="#">**Banned**</a></li>
            <li><a href="#">**Banned**</a></li>
            <li><a href="#">**Banned**</a></li>
            <li><a href="#">**Banned**</a></li>
            <%
                }
            %>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%
                if(nav_passport != null){
            %>
            <li id="nav-user"><a href="../user.jsp"><%=nav_passport.getName()%></a></li>
            <li><a href="../index.jsp?action=logout">登出</a></li>
            <%
            }else{
            %>
            <li><a href="../index.jsp">登录</a></li>
            <%
                }
            %>
        </ul>
    </div>
</nav>
</body>
</html>
