<%@ page import="cn.lncsoftware.data.User" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article | Management</title>
</head>
<body>
<h1>Article Management</h1>
<%
    User passport = (User)session.getAttribute("passport");
    String statusFlag = "";
    String action = request.getParameter("action");

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
%>
<form action="AritcleManagement.jsp" method="post">
    <label>Search: <input type="text" name="keyword" value=""></label>
</form>
<%
    }
%>
</body>
</html>
