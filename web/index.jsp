<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.common.RegexTools" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="java.util.Collections" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %>
<%@ page import="cn.lncsoftware.common.StringTools" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/3
  Time: 下午5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园 首页</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<script src="js/tools.js"></script>
<%
    User passport = (User)session.getAttribute("passport");
    request.setCharacterEncoding("utf-8");
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
        <h1>软件园协会 <small>at 广东岭南职业技术学院</small></h1>
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
        <div class="col-sm-8">
            <%
                bulletin = Bulletin.getDao().getBulletinBoard("mainPageTopBoard");
                if(bulletin != null){
            %>
            <div class="container-fluid">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <div class="media">
                            <%
                                if(bulletin.getImageLink() != null && !"".equals(bulletin.getImageLink())){
                            %>
                            <div class="media-left">
                                <div class="media-object">
                                    <a href="<%=bulletin.getLink()%>"><img src="<%=bulletin.getImageLink()%>" onload="DrawImage(this,128,128)"></a>
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="media-body">
                                <p id="topBoard"><%=bulletin.getContext()%></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <div class="container-fluid">
                <div class="list-group">
                    <%
                        List<Article> articles = Article.getDao().getLatestPage();
                        if(articles == null || articles.size() == 0){
                    %>
                    <a class="list-group-item list-group-item-heading disabled">噢，现在我们还没有任何文章</a>
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
                    <a class="list-group-item" href="article.jsp?action=details&articleID=<%=article.getObjectId().toHexString()%>">
                        <div class="media">
                            <div class="media-body">
                                <h4><%=article.getTitle()%></h4>
                                <p><%=article.getPreviewSentences()%></p>
                                <small>By:<%=author%> at <%=StringTools.convertDate(article.getDate())%></small>
                            </div>
                        </div>
                    </a>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="container-fluid">
                <div class="panel <%=(passport == null ? "panel-primary" : "panel-success")%>">
                    <div class="panel-heading">用户</div>
                    <div class="panel-body">
                        <div class="container-fluid">
                            <%
                            String bannerText = "";
                                if(!statusFlag.equals("")){
                                    String alertType = "";
                                    switch (statusFlag){
                                        case "authentication failed":
                                            alertType = "alert-warning";
                                            bannerText = "认证失败";
                                            break;
                                        case "authentication banned":
                                            alertType = "alert-danger";
                                            bannerText = "抱歉，你被禁止登陆";
                                            break;
                                        case "authentication success":
                                            alertType= "alert-success";
                                            bannerText = "认证成功";
                                            break;
                                        case "logout successful":
                                            alertType= "alert-success";
                                            bannerText = "您已登出";
                                            break;
                                    }
                            %>
                            <div class="alert <%=alertType%>"><%=bannerText%></div>
                            <%
                            }

                            if(passport == null){
                            %>
                            <form class="form-horizontal" action="index.jsp" method="post">
                                <input type="hidden" name="action" value="login">
                                <div class="form-group">
                                    <input class="form-control" placeholder="用户名" type="text" name="username" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" type="password" name="password" value="">
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-success col-sm-12" value="登陆">
                                </div>
                                <div class="form-group">
                                    <a class="btn btn-default col-sm-12" href="register.jsp">注册</a>
                                </div>
                            </form>
                            <%
                            }else{
                            %>
                            <b><%=passport.getName()%></b><br>
                            <%
                                for(String s : passport.getRights()){
                                    switch (s){
                                        case "login":
                            %>
                            <label class="label label-success hidden-sm">Login</label>
                            <%
                                            break;

                                        case "article":
                            %>
                            <label class="label label-info">Writer</label>
                            <%
                                            break;

                                        case "admin":
                            %>
                            <label class="label label-danger">Admin</label>
                            <%
                                    }
                                }
                            }
                            %>
                        </div>
                    </div>
                    <%
                        if(passport != null){
                    %>
                    <div class="list-group">
                        <a class="list-group-item" href="user.jsp"><span class="glyphicon glyphicon-user"></span> 用户中心</a>
                        <%
                            for(String s : passport.getRights()){
                                if(s.equals("admin")){
                        %>
                        <a class="list-group-item" href="admin.sub/index.jsp"><span class="glyphicon glyphicon-wrench"></span> 管理中心</a>
                        <%
                                }
                                if(s.equals("article")){
                        %>
                        <a class="list-group-item" href="writeArticle.jsp?action=new"><span class="glyphicon glyphicon-edit"></span> 写文章</a>
                        <%
                                }
                            }
                        %>
                        <a class="list-group-item list-group-item-warning" href="index.jsp?action=logout"><span class="glyphicon glyphicon-log-out"></span> 登出</a>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/markdown.min.js"></script>
<script>
    context = $("#topBoard").html();
    $("#topBoard").html(markdown.toHTML(context));
</script>
</body>
</html>
