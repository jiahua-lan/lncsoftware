<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="org.bson.types.ObjectId" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/7
  Time: 下午10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write Article</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<%
    User passport = (User)session.getAttribute("passport");
    String action = request.getParameter("action");
    String statusFlag = "";
    if(passport == null){
        statusFlag = "no login";
}else{
    for(String s : passport.getRights()){
        if("article".equals(s)){
            statusFlag = "permission recognized";
            break;
        }
        statusFlag = "permission denied";
    }

    switch (statusFlag){
        case "permission recognized":{
            if(action != null){
                switch (action){
                    case "upload":{
                        String title = request.getParameter("title");
                        String tags = request.getParameter("tags");
                        String context = request.getParameter("context");
                        if(title != null && title.length() < 64 && !"".equals(title.trim())){
                            Article article = new Article();
                            article.setTitle(title);
                            if(tags != null && !"".equals(tags.trim())){
                                ArrayList<String> arrayList = new ArrayList<>();
                                Collections.addAll(arrayList,tags.split(";"));
                                article.setTags(arrayList);
                            }
                            article.setContext(context);
                            article.setAuthor(passport.getObjectId());
                            article.setDate(new Date());
                            article.setStatus("show");
                            Article.getDao().insert(article);
                            statusFlag = "submit success";
                        }else{
                            statusFlag = "illegal title";
                        }
                    };break;
                }
            }
        };break;
    }
}
%>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <script>
        document.getElementById("nav-article").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>Write Article</h1>
    </div>
    <div class="container-fluid">
        <%
            switch (statusFlag){
                case "no login":
        %>
        <div class="alert alert-warning">Please <a class="alert-link" href="index.jsp">Login</a></div>
        <%
                    break;

                case "permission recognized":
                    break;

                case "permission denied":
        %>
        <div class="alert alert-danger">You have no permission to write articles.</div>
        <%
                    break;

                case "submit success":
        %>
        <div class="alert alert-success">Article submit successful.</div>
        <%
                    break;

                case "illegal title":
        %>
        <div class="alert alert-warning">Title illegal.</div>
        <%
                    break;
            }

            if(passport != null && !"permission denied".equals(statusFlag)){
        %>
        <form class="form-horizontal" action="writeArticle.jsp" method="post">
            <input type="hidden" name="action" value="upload">
            <div class="form-group">
                <label class="control-label">Title </label>
                <input class="form-control" type="text" name="title" placeholder="less than 64 chars">
            </div>
            <div class="form-group">
                <label class="control-label">Tags:</label>
                <input class="form-control" type="text" name="tags" placeholder="tags1;tags2;">
            </div>
            <div class="form-group">
                <label class="control-label">Context:</label>
                <textarea class="form-control" name="context"></textarea>
            </div>
            <div class="form-group">
                <input class="btn btn-success" type="submit" value="submit">
            </div>
        </form>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
