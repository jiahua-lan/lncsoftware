<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="java.util.Collections" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %><%--
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
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <script>
        document.getElementById("nav-article").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>Article</h1>
        <%
            Bulletin bulletin = Bulletin.getDao().getBulletinBoard("article");
            if(bulletin != null){
        %>
        <p><%=bulletin.getContext()%></p>
        <%
            }
        %>
    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="list-group">
                <%
                    String spage = request.getParameter("page");
                    int ipage = 1;
                    if(spage != null){
                        ipage = Integer.parseInt(spage);
                    }
                    List<Article> articles = Article.getDao().getPage(ipage);
                    if(articles == null || articles.size() == 0){
                %>
                <a class="list-group-item disabled" href="#">Ooops, no articles here.</a>
                <%
                }else{
                    Collections.reverse(articles);
                    for(Article article : articles){
                        String author = User.getDao().get(article.getAuthor()).getName();
                        if(author == null) author = "**User not exist**";
                %>
                <a class="list-group-item" href="article.jsp?action=details&articleID=<%=article.getObjectId().toHexString()%>">
                    <div class="media">
                        <div class="media-body">
                            <h4><%=article.getTitle()%> <small>by :<%=author%></small></h4>
                            <%=article.getPreviewSentences()%>
                        </div>
                    </div>
                </a>
                <%
                    }
                %>
            </div>
            <%
                int totalPages = Article.getDao().getPages();
                if(totalPages > 0){
            %>
            <nav>
                <ul class="pagination">
                    <%
                        for(int i = 0; i < totalPages; i++){
                    %>
                    <li><a href="article.jsp?page=<%=i + 1%>"><%=i + 1%></a></li>
                    <%
                        }
                    %>
                </ul>
            </nav>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
