<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.data.common.RegexTools" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h1 class="text-center">Register</h1>
                    <%
                        User passport = (User)session.getAttribute("passport");
                        String action = request.getParameter("action");

                        if(passport != null){
                    %>
                    <div class="alert alert-warning">You already registered. <a class="alert-link" href="index.jsp">Back to Homepage</a></div>
                    <%
                    }else{
                        if(action != null){
                            if(action.equals("register")){
                                String username = request.getParameter("username");
                                String password = request.getParameter("password");
                                String contact = request.getParameter("contact");
                                if(RegexTools.legalUsername(username) && RegexTools.legalPassword(password) & RegexTools.legalContactInfo(contact)){
                                    if(User.getDao().getUserByName(username) != null){
                    %>
                    <div class="alert alert-warning">User exists.
                        <a class="alert-link" href="register.jsp">Back to register page</a> or <a class="alert-link" href="index.jsp">Back to Homepage</a>
                    </div>
                    <%
                    }else{
                        User user = new User();
                        user.setName(username);
                        user.setPassword(password);
                        user.setContactInfo(contact);
                        ArrayList<String> arrayList = new ArrayList<>();
                        Collections.addAll(arrayList, "login","article");
                        user.setRights(arrayList);
                        User.getDao().insert(user);
                    %>
                    <div class="alert alert-success">Register success. <a class="alert-link" href="index.jsp">Back to Homepage</a></div>
                    <%
                        }
                    }else{
                    %>
                    <div class="alert alert-warning">Register failed, there are one or more legal fields.<br>
                        <a class="alert-link" href="register.jsp">Back to register page</a> or <a class="alert-link" href="index.jsp">Back to Homepage</a>
                    </div>
                    <%
                            }
                        }
                    }else{
                    %>
                    <form class="form-horizontal" method="post" action="register.jsp">
                        <div class="container-fluid">
                            <input type="hidden" name="action" value="register">
                            <div class="form-group">
                                <input type="text" class="form-control" name="username" placeholder="Username">
                                <label>A-Z a-z and 0-9, also &quot;-&quot; and &quot;_&quot;, 6 - 32 chars</label>
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control" placeholder="Password">
                                <label>A-Z a-z and 0-9, = &lt; &gt; , _ @ &amp; % # &quot; ! &yen; are legal.</label>
                            </div>
                            <div class="form-group">
                                <input type="text" name="contact" class="form-control" placeholder="Contact info">
                                <label>Email address or QQ number</label>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-success col-md-12" value="Register">
                            </div>
                            <div class="form-group">
                                <a href="index.jsp" class="btn btn-warning col-md-12">Back to Homepage</a>
                            </div>
                        </div>
                    </form>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
