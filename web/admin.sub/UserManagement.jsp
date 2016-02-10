<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.factory.UserDAO" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="cn.lncsoftware.data.common.RegexTools" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User | Management</title>
</head>
<body>
<h1>User Management</h1>
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
        if(statusFlag.equals("permission recognition")){
%>
<form action="UserManagement.jsp" method="post">
    <input type="hidden" name="action" value="search">
    <label>Search: <input type="text" name="keyword" placeholder="part of username..."> <input type="checkbox" name="useRegex" value="useRegex">use regex <input type="submit" value="Go!"></label>
</form>
<hr>
<%
            if(action != null){
                switch (action){
                    case "search":{
                        String keyword = request.getParameter("keyword");
                        boolean useRegex = "useRegex".equals(request.getParameter("useRegex"));
                        if(keyword != null && (keyword.matches("[\\d\\w\\-_]{1,32}") || useRegex)){
                            List<User> users = User.getDao().search("name",(useRegex ? keyword : (".*"+keyword+".*")));
                            if(users != null && users.size() > 0){
%>
<%=users.size()%> result(s) with <%=keyword%><br><br>
<%
                                for(User user : users){
%>
<form action="UserManagement.jsp" method="post">
    <input type="hidden" name="userID" value="<%=user.getObjectId().toHexString()%>">
    <input type="hidden" name="action" value="update">
    <label>Username: <input type="text" name="username" value="<%=user.getName()%>"></label><br>
    <label>Rights:
        <input type="checkbox" name="rights" value="login" <%=(user.getRights().contains("login") ? "checked" : "")%>>login
        <input type="checkbox" name="rights" value="admin" <%=(user.getRights().contains("admin") ? "checked" : "")%>>admin
        <input type="checkbox" name="rights" value="article" <%=(user.getRights().contains("article") ? "checked" : "")%>>article
    </label><br>
    <label>Contact: <input type="text" name="contact" value="<%=user.getContactInfo()%>"></label><br>
    <input type="submit" value="update change">
</form>
<%
                                }
                            }else{
%>
No result.
<%
                            }
                        }else{
%>
Keyword illegal.<br>
<%
                        }
                    };break;

                    case "update":{
                        String userID = request.getParameter("userID");
                        if(userID != null){
                            User user = User.getDao().get(new ObjectId(userID));
                            if(user != null){
                                String username = request.getParameter("username");
                                String contact = request.getParameter("contact");
                                String[] rights = request.getParameterValues("rights");
                                if(RegexTools.legalUsername(username) && RegexTools.legalContactInfo(contact)){
                                    user.setName(username);
                                    user.setContactInfo(contact);
                                    if(rights != null){
                                        ArrayList<String> arrayList = new ArrayList<>();
                                        Collections.addAll(arrayList,rights);
                                        user.setRights(arrayList);
                                        User.getDao().update(user);
%>Update Successful<br><%
                                    }
                                }else{
%>
illegal field(s).<br>
<%
                                }
                            }else{
%>
Ooops, user not exist.<br>
<%
                            }
                        }
                    };break;
                }
            }
        }
    }
%>
</body>
</html>
