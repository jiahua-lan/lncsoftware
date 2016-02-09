<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/7
  Time: 上午1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article</title>
</head>
<body>
<h1>Article</h1>
<a href="writeArticle.jsp?action=new">Write new Article</a><br>
<hr>
<%
    String spage = request.getParameter("page");
    int ipage = 1;
    if(spage != null){
        ipage = Integer.parseInt(spage);
    }
    List<Article> articles = Article.getDao().getPage(ipage);
    if(articles == null || articles.size() == 0){
%>
Ooops, no articles here.<br>
    <%
    }else{
        Collections.reverse(articles);
        for(Article article : articles){
            String author = User.getDao().get(article.getAuthor()).getName();
            if(author == null) author = "**User not exist**";
%>
<label><%=article.getTitle()%></label> <small>by :<%=author%></small><br>
<p><%=article.getPreviewSentences()%> <a href="article.jsp?actioin=details&articleID=<%=article.getObjectId().toHexString()%>">More&gt;</a></p>
<hr>
<%
        }
        int totalPages = Article.getDao().getPages();
        if(totalPages > 0){
            for(int i = 0; i < totalPages; i++){
%>[<a href="article.jsp?page=<%=i + 1%>"><%=i + 1%></a>]<%
            }
        }
    }
%>
</body>
</html>
