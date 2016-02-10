<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.data.common.RegexTools" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/11
  Time: 上午1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<%
    User passport = (User)session.getAttribute("passport");
    String action = request.getParameter("action");

    if(passport == null){
%>
Please <a href="index.jsp">Login</a>.
<%
    }else{
        if(action != null){
            switch (action){
                case "update":{
                    String contact = request.getParameter("contact");
                    String password = request.getParameter("password");
                    if(RegexTools.legalPassword(password)){
                        passport.setPassword(password);
                    }else if(passport != null && !"".equals(password)){
%>
Illegal Password.<br>
<%
                        break;
                    }
                    if(RegexTools.legalContactInfo(contact)){
                        passport.setContactInfo(contact);
                        User.getDao().update(passport);
                        session.setAttribute("passport",passport);
%>
Update success.<br>
<%
                    }else{
%>
Illegal contact Info.<br>
<%
                    }
                }
            }
        }

%>
<form action="user.jsp" method="post">
    <input type="hidden" name="action" value="update">
    <label>Username: <%=passport.getName()%></label><br>
    <label>Password: <input type="password" name="password" placeholder="Fill nothing if not change it."></label><br>
    <label>Contact info: <input type="text" name="contact" value="<%=passport.getContactInfo()%>"></label><br>
    <input type="submit" value="update">
</form>
<%
    }
%>
</body>
</html>
