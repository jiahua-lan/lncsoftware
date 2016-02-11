<%@ page import="cn.lncsoftware.data.User" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/12
  Time: 上午1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <div class="navbar-brand">LNCSA</div>
            </div>
            <ul class="nav navbar-nav">
                <li id="nav-home"><a href="index.jsp">Home</a></li>
                <li id="nav-app"><a href="app.jsp">App</a></li>
                <li id="nav-article"><a href="article.jsp">Article</a></li>
                <li id="nav-contact"><a href="contact.jsp">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%
                    User nav_passport = (User)session.getAttribute("passport");
                    if(nav_passport != null){
                %>
                <li id="nav-user"><a href="user.jsp"><%=nav_passport.getName()%></a></li>
                <li><a href="index.jsp?action=logout">Logout</a></li>
                <%
                    }else{
                %>
                <li><a href="register.jsp">Register</a></li>
                <li><a href="index.jsp">Login</a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </nav>
</body>
</html>
