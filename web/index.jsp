<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.common.RegexTools" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="java.util.Collections" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %><%--
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
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
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
                        passport = null;
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
%>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <script>
        document.getElementById("nav-home").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>LingNan College Software(Center) Association</h1>
        <%
            Bulletin bulletin = Bulletin.getDao().getBulletinBoard("mainPage");
            if(bulletin != null){
        %>
        <p><%=bulletin.getContext()%></p>
        <%
            }
        %>
    </div>

    <div class="row">
        <div class="col-md-8">
            <div class="container-fluid">
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
                <div class="media">
                    <div class="media-body">
                        <h4><a href="article.jsp?actioin=details&articleID=<%=article.getObjectId().toHexString()%>"><%=article.getTitle()%></a></h4>
                        <%=article.getPreviewSentences()%>
                        <hr>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <div class="col-md-4">
            <div class="container-fluid">
                <div class="panel <%=(passport == null ? "panel-primary" : "panel-success")%>">
                    <div class="panel-heading">Login</div>
                    <div class="panel-body">
                        <div class="container-fluid">
                            <%
                                if(!statusFlag.equals("")){
                                    String alertType = "";
                                    switch (statusFlag){
                                        case "authentication failed":
                                            alertType = "alert-warning";
                                            break;
                                        case "authentication banned":
                                            alertType = "alert-danger";
                                            break;
                                        case "authentication success":
                                            alertType= "alert-success";
                                            break;
                                        case "logout successful":
                                            alertType= "alert-success";
                                            break;
                                    }
                            %>
                            <div class="alert <%=alertType%>"><%=statusFlag%></div>
                            <%
                            }

                            if(passport == null){
                            %>
                            <form class="form-horizontal" action="index.jsp" method="post">
                                <input type="hidden" name="action" value="login">
                                <div class="form-group">
                                    <input class="form-control" placeholder="Username" type="text" name="username" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" type="password" name="password" value="">
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-success col-md-12" value="Login">
                                </div>
                                <div class="form-group">
                                    <a class="btn btn-default col-md-12" href="register.jsp">Register</a>
                                </div>
                            </form>
                            <%
                            }else{
                            %>
                            <b>Welcome, <%=passport.getName()%>
                            <%
                                for(String s : passport.getRights()){
                                    switch (s){
                                        case "article":
                            %>
                            <span class="label label-info">Writer</span>
                            <%
                                            break;

                                        case "admin":
                            %>
                            <span class="label label-danger">Admin</span>
                            <%
                                    }
                                }
                            %>
                            </b>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <%
                        if(passport != null){
                    %>
                    <div class="list-group">
                        <a class="list-group-item" href="user.jsp"><span class="glyphicon glyphicon-user"></span> User center</a>
                        <%
                            for(String s : passport.getRights()){
                                if(s.equals("admin")){
                        %>
                        <a class="list-group-item" href="admin.sub/adminCenter.jsp"><span class="glyphicon glyphicon-wrench"></span> Management Center</a>
                        <%
                                }
                                if(s.equals("article")){
                        %>
                        <a class="list-group-item" href="writeArticle.jsp?action=new"><span class="glyphicon glyphicon-edit"></span> Write new Article</a>
                        <%
                                }
                            }
                        %>
                        <a class="list-group-item list-group-item-warning" href="index.jsp?action=logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
