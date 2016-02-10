<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/11
  Time: 上午12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact</title>
</head>
<body>
<h2>Contact us</h2>
<%
    List<Bulletin> contact = Bulletin.getDao().getBulletinItems("contactInfo");
    if(contact != null && contact.size() > 0){
        for (Bulletin b : contact){
%>
<img src="<%=b.getImageLink()%>" width="60pt" height="60pt"><br>
<a href="<%=b.getLink()%>"><%=b.getContext()%></a>
<hr>
<%
        }
    }else{
%>
Nothings here...<br>
<%
    }
%>
<h2>Friends link</h2>
<%
    contact = Bulletin.getDao().getBulletinItems("contactFriendLink");
    if(contact != null && contact.size() > 0){
        for(Bulletin bulletin : contact){
%>
<a href="<%=bulletin.getLink()%>"><%=bulletin.getContext()%></a><br>
<%
        }
    }
%>
</body>
</html>
