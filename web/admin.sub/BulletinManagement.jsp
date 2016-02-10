<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bulletin | Management</title>
</head>
<body>
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

    if(!statusFlag.equals("")){
%><%=statusFlag%><%
    }

    if(passport != null && "permission recognition".equals(statusFlag)){
%>
<a href="BulletinManagement.jsp?type=mainPage">MainPage</a> |
<a href="BulletinManagement.jsp?type=article">Article</a> |
<a href="BulletinManagement.jsp?type=app">App</a> |
<a href="BulletinManagement.jsp?type=contact">Contact</a>
<%
        if(action != null){
            switch (action){
                case "update":{
                    String bulletinID = request.getParameter("bulletinID");
                    if(bulletinID != null){
                        Bulletin bulletin = Bulletin.getDao().get(new ObjectId(bulletinID));
                        if(bulletin != null){
                            String context = request.getParameter("context");
                            if(context != null && !"".equals(context)){
                                bulletin.setContext(context);
                                bulletin.setDate(new Date());
                                Bulletin.getDao().update(bulletin);
%>
Update successful.<br>
<%
                            }else{
%>
Context is required.<br>
<%
                            }
                        }else{
%>
This bulletin was deleted....<br>
<%
                        }
                    }
                };break;

                case "add":{
                    String type = request.getParameter("btype");
                    if(type != null){
                        switch (type){
                            case "mainPage":
                            case "article":
                            case "app_guide":
                            case "app_info":{
                                if(Bulletin.getDao().getBulletinBoard(type) == null){
                                    String context = request.getParameter("context");
                                    if(context != null && !"".equals(context)){
                                        Bulletin bulletin = new Bulletin();
                                        bulletin.setType(type);
                                        bulletin.setContext(context);
                                        bulletin.setDate(new Date());
                                        Bulletin.getDao().insert(bulletin);
%>
Add successful.<br>
<%
                                    }else{
%>
Context is required.<br>
<%
                                    }
                                }else{
%>
This bulletin was exist.<br>
<%
                                }
                            };break;

                            case "contactInfo":
                            case "contactFriendLink":{
                                String context = request.getParameter("context");
                                if(context != null && !"".equals(context)){
                                    String link = request.getParameter("link");
                                    if(link != null || !"".equals(link)){
                                        Bulletin bulletin = new Bulletin();
                                        bulletin.setType(type);
                                        bulletin.setContext(context);
                                        bulletin.setDate(new Date());
                                        bulletin.setLink(link);
                                        bulletin.setImageLink(request.getParameter("imageLink"));
                                        Bulletin.getDao().insert(bulletin);
%>
Add successful<br>
<%
                                    }else{
%>
Link was required.<br>
<%
                                    }
                                }else{
%>
Context was required.<br>
<%
                                }
                            };break;

                            default:{
%>
Illegal type.<br>
<%
                            };break;
                        }
                    }
                };break;

                case "delete":{
                    String bulletinID = request.getParameter("bulletinID");
                    if(bulletinID != null){
                        Bulletin bulletin = Bulletin.getDao().get(new ObjectId(bulletinID));
                        if(bulletin != null){
                            Bulletin.getDao().remove(bulletin);
%>
Delete successful.<br>
<%
                        }
                    }
                };break;
            }
        }

        String type = request.getParameter("type");
        if(type == null || "".equals(type)) type = "mainPage";
        switch (type){
            case "mainPage":
            case "article": {
                Bulletin bulletin = Bulletin.getDao().getBulletinBoard(type);
                if(bulletin != null){
%>
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="type" value="<%=type%>">
    <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
    <label>Context:
        <textarea name="context"><%=bulletin.getContext()%></textarea>
    </label><br>
    <input type="submit" value="update">
</form>
<%
                }else{
%>
Create :
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="type" value="<%=type%>">
    <input type="hidden" name="btype" value="<%=type%>">
    <label>Context:
        <textarea name="context"></textarea>
    </label>
    <input type="submit" value="create">
</form>
<%
                }
            };break;

            case "app":{
                Bulletin bulletin = Bulletin.getDao().getBulletinBoard("app_guide");
                if(bulletin != null){
%>
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="type" value="<%=type%>">
    <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
    <label>Context:
        <textarea name="context"><%=bulletin.getContext()%></textarea>
    </label><br>
    <input type="submit" value="update">
</form>
<%
                }else{
%>
Create Guide:
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="type" value="<%=type%>">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="btype" value="app_guide">
    <label>Context:
        <textarea name="context"></textarea>
    </label>
    <input type="submit" value="create">
</form>
<%
                }

                bulletin = Bulletin.getDao().getBulletinBoard("app_info");
                if(bulletin != null){
%>
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="type" value="<%=type%>">
    <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
    <label>Context:
        <textarea name="context"><%=bulletin.getContext()%></textarea>
    </label><br>
    <input type="submit" value="update">
</form>
<%
                }else{
%>
Create Info:
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="type" value="<%=type%>">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="btype" value="app_info">
    <label>Context:
        <textarea name="context"></textarea>
    </label>
    <input type="submit" value="create">
</form>
<%
                }
            };break;

            case "contact":{
%>
<h2>Contact Info</h2>
<%
                List<Bulletin> bulletins = Bulletin.getDao().getBulletinItems("contactInfo");
                if(bulletins != null && bulletins.size() > 0){
                    for (Bulletin bulletin : bulletins){
%>
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="type" value="<%=type%>">
    <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
    <label>Context:
        <textarea name="context"><%=bulletin.getContext()%></textarea>
    </label><br>
    <label>Link: <input type="url" name="link" value="<%=bulletin.getLink()%>"></label><br>
    <label>ImageLink: <input type="url" name="imageLin" value="<%=bulletin.getImageLink()%>"></label><br>
    <input type="submit" value="update">
    <a href="BulletinManagement.jsp?action=delete&bulletinID=<%=bulletin.getObjectId().toHexString()%>&type=<%=type%>">Delete</a>
</form>
<%
                    }
                }
%>
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="btype" value="contactInfo">
    <input type="hidden" name="type" value="<%=type%>">
    <label>Context: <input type="text" name="context"></label><br>
    <label>Link: <input type="text" name="link"></label><br>
    <label>ImageLink <input type="url" name="imageLink"></label><br>
    <input type="submit" value="add">
</form>
<hr>
<h2>Friend Links</h2>
<%
                bulletins = Bulletin.getDao().getBulletinItems("contactFriendLink");
                if(bulletins != null && bulletins.size() > 0){
                    for (Bulletin bulletin : bulletins){
%>
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="type" value="<%=type%>">
    <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
    <label>Context:
        <textarea name="context"><%=bulletin.getContext()%></textarea>
    </label><br>
    <label>Link: <input type="url" name="link" value="<%=bulletin.getLink()%>"></label><br>
    <input type="submit" value="update">
    <a href="BulletinManagement.jsp?action=delete&bulletinID=<%=bulletin.getObjectId().toHexString()%>&type=<%=type%>">Delete</a>
</form>
<%
                    }
                }
%>
<form action="BulletinManagement.jsp" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="btype" value="contactFriendLink">
    <input type="hidden" name="type" value="<%=type%>">
    <label>Context: <input type="text" name="context"></label><br>
    <label>Link: <input type="text" name="link"></label><br>
    <input type="submit" value="add">
</form>
<%
            }
        }
    }
%>
</body>
</html>
