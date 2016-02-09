<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.common.StringTools" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="java.util.ArrayList" %><%--
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

    if(!statusFlag.equals("")){
%><%=statusFlag%><%
    }

    if(passport != null && "permission recognition".equals(statusFlag)){
%>
<form action="ArticleManagement.jsp" method="post">
    <input type="hidden" name="action" value="search">
    <label>Search:
        <input type="text" name="keyword" value="">
        <input type="checkbox" name="useRegex" value="useRegex">use regex
        <input type="submit" value="search">
    </label>
</form>
<hr>
<%
        if(action != null){
            switch (action){
                case "search":{
                    String keyword = request.getParameter("keyword");
                    String useRegex = request.getParameter("useRegex");
                    if(keyword != null){
                        List<Article> articleList = Article.getDao().find("useRegex".equals(useRegex) ? keyword : ".*"+keyword+".*");
                        if(articleList != null && articleList.size() > 0){
                            for(Article article : articleList){
                                User author = User.getDao().get(article.getAuthor());
                                String authorName = "**User not exist**";
                                if (author != null) authorName = author.getName();
%>
<form action="ArticleManagement.jsp" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="articleID" value="<%=article.getObjectId().toHexString()%>">
    <label>Title: <input type="text" name="title" value="<%=article.getTitle()%>"></label><br>
    <label>Author: <label><%=authorName%></label></label><br>
    <label>Date : <label><%=article.getDate().toString()%></label></label><br>
    <label>Tags: <input type="text" name="tags" value="<%=StringTools.listTags(article.getTags())%>"></label><br>
    <label>Context:<br><textarea name="context"><%=article.getContext()%></textarea></label><br>
    <label>Status:
        <input type="radio" name="status" value="show" <%=("show".equals(article.getStatus())) ? "checked" : ""%>>show
        <input type="radio" name="status" value="hidden" <%=("hidden".equals(article.getStatus()) ? "checked" : "")%>>hidden
    </label><br>
    <input type="submit" value="update">
    <a href="ArticleManagement.jsp?action=delete&articleID=<%=article.getObjectId().toHexString()%>">Delete</a><br>
</form>
<br>
<%
                            }
                        }else{
%>
No result<br>
<%
                        }
                    }
                };break;

                case "delete":{
                    String articleID = request.getParameter("articleID");
                    if(articleID != null){
                        Article.getDao().remove(Article.getDao().get(new ObjectId(articleID)));
%>
Delete success.<br>
<%
                    }else{
%>
Delete failed.<br>
<%
                    }
                };break;

                case "update":{
                    String articleID = request.getParameter("articleID");
                    if(articleID != null) {
                        Article article = Article.getDao().get(new ObjectId(articleID));
                        if(article != null){
                            String title = request.getParameter("title");
                            if(title != null && title.length() < 64 && !"".equals(title.trim())) {
                                article.setTitle(title);
                                String tags = request.getParameter("tags");
                                if (tags != null) article.setTags(StringTools.splitTags(tags));
                                String context = request.getParameter("context");
                                if (context != null) article.setContext(context);
                                String status = request.getParameter("status");
                                if (status != null) article.setStatus(status);
                                Article.getDao().update(article);
%>
Update success.<br>
<%
                            }else{
%>
Illegal title.<br>
<%
                            }
                        }else{
%>
Article not exist.<br>
<%
                        }
                    }
                };break;
            }
        }
    }
%>
</body>
</html>
