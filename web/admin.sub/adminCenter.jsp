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
</head>
<body>
<%
    User passport = (User)session.getAttribute("passport");
    String statusFlag = "";

    if(passport == null){
%>
Please <a href="../index.jsp">Login</a>.<br>
<%
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
            statusFlag = "permission recognition";
        }
    }

    if(passport != null){
        if(!statusFlag.equals("")){
%><%=statusFlag%><%
        }
        if(statusFlag.equals("permission recognition")){
%>
<label>Management:</label><br>
<a href="UserManagement.jsp">User</a>
<a href="AppInfoManagement.jsp">Application</a>
<a href="AritcleManagement.jsp">Article</a>
<a href="BulletinManagement.jsp">Bulletin</a>
<br>
<%
        }
    }
%>
</body>
</html>
