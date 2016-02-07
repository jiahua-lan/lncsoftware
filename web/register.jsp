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
</head>
<body>
<%
    User passport = (User)session.getAttribute("passport");
    String action = request.getParameter("action");

    if(passport != null){
%>
You already registered. <a href="index.jsp">Back to Homepage</a><br>
<%
    }else{
        if(action != null){
            if(action.equals("register")){
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String contact = request.getParameter("contact");
                if(RegexTools.legalUsername(username) && RegexTools.legalPassword(password) & RegexTools.legalContactInfo(contact)){
                    User user = new User();
                    user.setName(username);
                    user.setPassword(password);
                    user.setContactInfo(contact);
                    ArrayList<String> arrayList = new ArrayList<>();
                    Collections.addAll(arrayList, "login","article");
                    user.setRights(arrayList);
                    User.getDao().insert(user);
%>
Register success. <a href="index.jsp">Back to Homepage</a><br>
<%
                }else{
%>
Register failed, there are one or more legal fields. <a href="register.jsp">Back to register page.</a> or <a href="index.jsp">Back to Homepage.</a><br>
<%
                }
            }
        }else{
%>
<form method="post" action="register.jsp">
    <input type="hidden" name="action" value="register">
    <label>Username: <input type="text" name="username"></label><br>
    <small>A-Z a-z and 0-9, also &quot;-&quot; and &quot;_&quot;, 6 - 32 chars</small><br>
    <label>Password: <input type="password" name="password"></label><br>
    <small>A-Z a-z and 0-9, = &lt; &gt; , _ @ &amp; % # &quot; ! &yen; are legal.</small><br>
    <label>Contact: <input type="text" name="contact"></label><br>
    <small>Email address or QQ number</small><br>
    <input type="submit" value="Register"><br>
</form>
<%
}
    }
%>
</body>
</html>
