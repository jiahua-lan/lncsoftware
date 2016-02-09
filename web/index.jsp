<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.data.common.RegexTools" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/3
  Time: 下午5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h1>LingNan College Software(Center) Association</h1>
<%
    User passport = (User)session.getAttribute("passport");
    String action = request.getParameter("action");
    String statusFlag = "";

    if(action != null){
        switch (action){
            case "login":{
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if(RegexTools.legalUsername(username) && RegexTools.legalPassword(password)){
                    passport = User.getDao().getUserByName(username);
                    if(passport == null || !passport.getPassword().equals(password)){
                        statusFlag = "authentication failed";
                    }else{
                        boolean loginFlag = false;
                        for(String s : passport.getRights()){
                            if(s.equals("login")){
                                loginFlag = true;
                                break;
                            }
                        }
                        if(loginFlag){
                            session.setAttribute("passport",passport);
                            statusFlag = "authentication success";
                        }else{
                            session.removeAttribute("passport");
                            passport = null;
                            statusFlag = "authentication banned";
                        }
                    }
                }
            };break;

            case "logout":{
                session.removeAttribute("passport");
                passport = null;
                statusFlag = "logout successful";
            };break;
        }
    }

    if(!statusFlag.equals("")){
%><%=statusFlag%><br><%
    }

    if(passport == null){
%>
<form action="index.jsp" method="post">
    <input type="hidden" name="action" value="login">
    <label>Username: <input type="text" name="username" value=""></label><a href="register.jsp">Register</a><br>
    <label>Password: <input type="password" name="password" value=""></label><input type="submit" value="login">
</form>
<%
    }else{
%>
Welcome, <%=passport.getName()%>. <a href="index.jsp?action=logout">logout</a>
<%
        for(String s : passport.getRights()){
            if(s.equals("admin")){
%>
<a href="admin.sub/adminCenter.jsp">Management Center</a>
<%
            }
        }
    }
%>
<hr>
<%
    List<Article> articles = Article.getDao().getLatestPage();
    if(articles == null || articles.size() == 0){
%>
Ooops, no articles here.<br>
<%
    }else{
        Collections.reverse(articles);
        for(Article article : articles){
            String author;
            User user = User.getDao().get(article.getAuthor());
            if(user == null)
                author = "**User not exist**";
            else
                author = user.getName();
%>
<label><%=article.getTitle()%></label> <small>by :<%=author%></small><br>
<p><%=article.getPreviewSentences()%> <a href="article.jsp?actioin=details&articleID=<%=article.getObjectId().toHexString()%>">More&gt;</a></p>
<hr>
<%
        }
    }
%>
<a href="article.jsp">View More...</a>
</body>
</html>
